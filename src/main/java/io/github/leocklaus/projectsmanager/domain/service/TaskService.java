package io.github.leocklaus.projectsmanager.domain.service;

import io.github.leocklaus.projectsmanager.api.dto.*;
import io.github.leocklaus.projectsmanager.domain.exception.MemberNotFoundException;
import io.github.leocklaus.projectsmanager.domain.exception.TaskNotFoundException;
import io.github.leocklaus.projectsmanager.domain.exception.UserNotAuthorizedException;
import io.github.leocklaus.projectsmanager.domain.model.*;
import io.github.leocklaus.projectsmanager.domain.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final TaskMemberRepository taskMemberRepository;
    private final SubTaskRepository subTaskRepository;
    private final CommentRepository commentRepository;

    private final ProjectMemberRepository projectMemberRepository;

    public TaskService(TaskRepository taskRepository, ProjectService projectService, UserService userService, TaskMemberRepository taskMemberRepository, SubTaskRepository subTaskRepository, CommentRepository commentRepository, ProjectMemberRepository projectMemberRepository) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.userService = userService;
        this.taskMemberRepository = taskMemberRepository;
        this.subTaskRepository = subTaskRepository;
        this.commentRepository = commentRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    @Transactional
    public TaskOutputDTO addTask(TaskInputDTO dto, String projectId){
        var user = userService.getUserByIdOrThrowsExceptionIfNotExists(dto.leaderId());
        var project = projectService.getProjectByIdOrThrowsNotFoundExceptionIfNotExists(UUID.fromString(projectId));
        var projectMember = project.getMembers().stream().filter(member -> member.getUser().getId() == user.getId())
                .findFirst().orElseThrow(()-> new MemberNotFoundException());
        var task = new Task(dto);
        task.setProject(project);
        task = taskRepository.save(task);
        var leader = createTaskMember(task, projectMember, MemberType.LEADER);
        leader = taskMemberRepository.save(leader);
        task.addMember(leader);
        return new TaskOutputDTO(task);
    }

    @Transactional
    public void addTaskMember(String id, String memberId){
        var task = getTaskByIdOrThrowsExceptionIfNotExists(UUID.fromString(id));
        var projectMember =  task.getProject().getMembers().stream().filter(member -> member.getUser().getId().equals(UUID.fromString(memberId)))
                .findFirst().orElseThrow(()-> new MemberNotFoundException());
        var taskMember = createTaskMember(task, projectMember, MemberType.MEMBER);
        taskMemberRepository.save(taskMember);
    }

    @Transactional
    private TaskMember createTaskMember(Task task, ProjectMember projectMember, MemberType memberType){
        return new TaskMember(new TaskMemberKey(projectMember.getId(), task.getId()), projectMember, task, memberType);
    }

    @Transactional
    public List<TaskShortOutputDTO> getTasksByProjectId(String id) {
        var project = projectService.getProjectByIdOrThrowsNotFoundExceptionIfNotExists(UUID.fromString(id));
        var tasks = project.getTasks();

        return tasks.stream().map(task -> {
            var subtasks = task.getSubTasks();
            var completedSubsTasksNumber = subtasks.stream().filter(subtask -> subtask.isChecked()).count();
            var numberOfMembers = task.getTaskMembers().size();
            return new TaskShortOutputDTO(task, subtasks.size(), completedSubsTasksNumber, numberOfMembers);
        }).toList();
    }

    private Task getTaskByIdOrThrowsExceptionIfNotExists(UUID taskId){
        var task = taskRepository.findById(taskId)
                .orElseThrow(()-> new TaskNotFoundException(taskId));
        return task;
    }

    private SubTask getSubTaskByIdOrThrowsExceptionIfNotExists(UUID taskId){
        var subTask = subTaskRepository.findById(taskId)
                .orElseThrow(()-> new TaskNotFoundException(taskId));
        return subTask;
    }

    @Transactional
    public SubTaskOutputDTO addSubTask(String taskId, SubTaskInputDTO dto) {
        var task = getTaskByIdOrThrowsExceptionIfNotExists(UUID.fromString(taskId));
        var subtask = new SubTask(dto);
        subtask.setTask(task);
        subtask = subTaskRepository.save(subtask);
        return new SubTaskOutputDTO(subtask);
    }

    @Transactional
    public void checkSubTask(String id, String subtaskId) {

        var task = getTaskByIdOrThrowsExceptionIfNotExists(UUID.fromString(id));
        var subtask = getSubTaskByIdOrThrowsExceptionIfNotExists(UUID.fromString(subtaskId));

        if(!subtask.getTask().getId().equals(task.getId())){
            throw new UserNotAuthorizedException();
        }

        subtask.setChecked();

        subTaskRepository.save(subtask);

    }

    @Transactional
    public void uncheckSubTask(String id, String subtaskId) {

        var task = getTaskByIdOrThrowsExceptionIfNotExists(UUID.fromString(id));
        var subtask = getSubTaskByIdOrThrowsExceptionIfNotExists(UUID.fromString(subtaskId));

        if(!subtask.getTask().getId().equals(task.getId())){
            throw new UserNotAuthorizedException();
        }

        subtask.setUnchecked();

        subTaskRepository.save(subtask);

    }

    @Transactional
    public void setTaskInProgress(String id) {
        var task = getTaskByIdOrThrowsExceptionIfNotExists(UUID.fromString(id));
        task.setInProgress();
        taskRepository.save(task);
    }

    @Transactional
    public void setTaskInRevision(String id) {
        var task = getTaskByIdOrThrowsExceptionIfNotExists(UUID.fromString(id));
        task.setUnderRevision();
        taskRepository.save(task);
    }

    @Transactional
    public void setTaskCompleted(String id) {
        var task = getTaskByIdOrThrowsExceptionIfNotExists(UUID.fromString(id));
        task.setCompleted();
        taskRepository.save(task);
    }

    @Transactional
    public CommentOutputDTO addComment(String id, CommentInputDTO dto) {
        var task = getTaskByIdOrThrowsExceptionIfNotExists(UUID.fromString(id));
        var user = userService.getUserByIdOrThrowsExceptionIfNotExists(dto.userId().toString());

        var comment = new Comment(dto);
        comment.setUser(user);
        comment.setTask(task);


        if(dto.rootCommentId() != null){
            var rootComment = commentRepository.findById(dto.rootCommentId());
            comment.setRootComment(rootComment.get());
        } else {
            comment.setRootComment(null);
        }

        return new CommentOutputDTO(
                comment.getId(),
                new UserOutputDTO(user),
                task.getId(),
                comment.getContent(),
                comment.getRootComment() != null ? comment.getRootComment().getId():null,
                comment.getCommentType()
        );


    }
}
