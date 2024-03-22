package io.github.leocklaus.projectsmanager.domain.service;

import io.github.leocklaus.projectsmanager.api.dto.*;
import io.github.leocklaus.projectsmanager.domain.exception.MemberNotFoundException;
import io.github.leocklaus.projectsmanager.domain.exception.ProjectNotFoundException;
import io.github.leocklaus.projectsmanager.domain.exception.UserNotAuthorizedException;
import io.github.leocklaus.projectsmanager.domain.exception.UserNotFoundException;
import io.github.leocklaus.projectsmanager.domain.model.*;
import io.github.leocklaus.projectsmanager.domain.projection.ProjectTasksProjection;
import io.github.leocklaus.projectsmanager.domain.repository.ProjectMemberRepository;
import io.github.leocklaus.projectsmanager.domain.repository.ProjectRepository;
import io.github.leocklaus.projectsmanager.domain.repository.TaskRepository;
import io.github.leocklaus.projectsmanager.domain.service.strategy.NewCommentNotificationStrategy;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ProjectMemberRepository projectMemberRepository;

    private final TaskRepository taskRepository;


    public ProjectService(ProjectRepository projectRepository, UserService userService, ProjectMemberRepository projectMemberRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.projectMemberRepository = projectMemberRepository;
        this.taskRepository = taskRepository;
    }

    public Page<ProjectShortOutputDTO> getAllProjectsPaged(Pageable pageable){
        Page<Project> projects = projectRepository.findAll(pageable);

        return projects.map(ProjectShortOutputDTO::new);
    }

    @Transactional
    public ProjectFullOutputDTO getProjectById(String id){

        var project = getProjectByIdOrThrowsNotFoundExceptionIfNotExists(UUID.fromString(id));
        var projectDto = setProjectFullDetails(project);

        return projectDto;
    }

    @Transactional
    public ProjectFullOutputDTO addNewProject(ProjectInputDTO dto){

        var leader = userService.getUserByIdOrThrowsExceptionIfNotExists(dto.leaderId().toString());

        var project = new Project(dto);

        project = projectRepository.save(project);

        var member = createMember(project, leader, MemberType.LEADER);

        member = projectMemberRepository.save(member);

        project.addMember(member);


        return setProjectFullDetails(project);

    }

    @Transactional
    protected Project getProjectByIdOrThrowsNotFoundExceptionIfNotExists(UUID id){
        return projectRepository.findById(id)
                .orElseThrow(()-> new ProjectNotFoundException(id));
    }

    @Transactional
    protected ProjectMember getProjectMemberOrThrowsExceptionIfNotExists(User member, Project project){
        return projectMemberRepository.findByUserAndProject(member, project)
                .orElseThrow(()-> new MemberNotFoundException());
    }

    private ProjectFullOutputDTO setProjectFullDetails(Project project){
        var members = project.getMembers();

        List<UserShortOutputDTO> membersDTO = members.stream().map(member -> {
            return new UserShortOutputDTO(member.getUser().getId(), member.getUser().getName(), member.getMemberType());
        }).toList();

        List<ProjectTasksProjection> tasks = taskRepository.getProjectTasksWithDetails(project);


        return new ProjectFullOutputDTO(project, membersDTO, tasks);
    }

    private ProjectMember createMember(Project project, User member, MemberType type){
        return new ProjectMember(new ProjectMemberKey(member.getId(), project.getId()),
                member, project, type);
    }


    @Transactional
    public void setProjectCompleted(String id) {
        var project = getProjectByIdOrThrowsNotFoundExceptionIfNotExists(UUID.fromString(id));
        project.setCompleted();
        projectRepository.flush();
    }

    @Transactional
    public void setProjectUnderReview(String id) {
        var project = getProjectByIdOrThrowsNotFoundExceptionIfNotExists(UUID.fromString(id));
        project.setUnderReview();
        projectRepository.flush();
    }

    public void setProjectPublic(String id) {
        var project = getProjectByIdOrThrowsNotFoundExceptionIfNotExists(UUID.fromString(id));
        project.setPublic();
        projectRepository.flush();
    }

    public void setProjectPrivate(String id) {
        var project = getProjectByIdOrThrowsNotFoundExceptionIfNotExists(UUID.fromString(id));
        project.setPrivate();
        projectRepository.flush();
    }

    @Transactional
    public void deleteProject(String id) {
        //TO BE IMPLEMENTED WITH CHECK FOR PROJECT LEADER
        var user = userService.getLoggedUser();
        var project = getProjectByIdOrThrowsNotFoundExceptionIfNotExists(UUID.fromString(id));
        var leader = project.getMembers().stream().filter(member -> member.getMemberType() == MemberType.LEADER)
                .findFirst().orElseThrow(()-> new UserNotFoundException("Member not found"));

        if(user.getId() != leader.getId().getUserId()){
            throw new UserNotAuthorizedException();
        }

        projectRepository.delete(project);
    }

    public void addMember(String id, String memberId) {
        var project = getProjectByIdOrThrowsNotFoundExceptionIfNotExists(UUID.fromString(id));
        var user = userService.getUserByIdOrThrowsExceptionIfNotExists(memberId);
        var member = createMember(project, user, MemberType.MEMBER);
        projectMemberRepository.save(member);
    }
}
