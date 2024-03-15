package io.github.leocklaus.projectsmanager.api.controller;

import io.github.leocklaus.projectsmanager.api.dto.CommentInputDTO;
import io.github.leocklaus.projectsmanager.api.dto.SubTaskOutputDTO;
import io.github.leocklaus.projectsmanager.api.dto.SubTaskInputDTO;
import io.github.leocklaus.projectsmanager.domain.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{id}/subtask")
    public ResponseEntity<SubTaskOutputDTO> addSubTask(@PathVariable String id, @RequestBody SubTaskInputDTO dto){
        SubTaskOutputDTO subTask = taskService.addSubTask(id, dto);
        URI uri = URI.create("/tasks/" + id + "/subtask/" + subTask.id());
        return ResponseEntity.created(uri).body(subTask);
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<?> addComment(@PathVariable String id, @RequestBody CommentInputDTO dto){
        var comment = taskService.addComment(id, dto);
        URI uri = URI.create("/tasks/" + id + "/comment/" + comment.commentId());
        return ResponseEntity.created(uri).body(comment);
    }

    @PutMapping("/{id}/progress")
    public ResponseEntity<?> setTaskInProgress(@PathVariable String id){
        this.taskService.setTaskInProgress(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/revision")
    public ResponseEntity<?> setTaskInRevision(@PathVariable String id){
        this.taskService.setTaskInRevision(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/completed")
    public ResponseEntity<?> setTaskCompleted(@PathVariable String id){
        this.taskService.setTaskCompleted(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/member/{memberId}")
    public ResponseEntity<?> addTaskMember(@PathVariable String id, @PathVariable String memberId){
        taskService.addTaskMember(id, memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/subtask/{subtaskId}/checked")
    public ResponseEntity<?> checkSubtask(@PathVariable String id, @PathVariable String subtaskId){
        taskService.checkSubTask(id, subtaskId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/subtask/{subtaskId}/checked")
    public ResponseEntity<?> uncheckSubTask(@PathVariable String id, @PathVariable String subtaskId){
        taskService.uncheckSubTask(id, subtaskId);
        return ResponseEntity.noContent().build();
    }

}
