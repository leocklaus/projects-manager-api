package io.github.leocklaus.projectsmanager.api.controller;

import io.github.leocklaus.projectsmanager.api.dto.*;
import io.github.leocklaus.projectsmanager.domain.service.ProjectService;
import io.github.leocklaus.projectsmanager.domain.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Page<ProjectShortOutputDTO>> getAllProjectsPaged(Pageable pageable){
        var projects = projectService.getAllProjectsPaged(pageable);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectFullOutputDTO> getProjectById(@PathVariable String id){

        var project = projectService.getProjectById(id);

        return ResponseEntity.ok(project);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskShortOutputDTO>> getProjectTasks(@PathVariable String id){
        List<TaskShortOutputDTO> tasks = taskService.getTasksByProjectId(id);

        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<?> addProject(@Valid @RequestBody ProjectInputDTO dto){

        var project = projectService.addNewProject(dto);

        URI uri = URI.create("/projects/" + project.id());

        return ResponseEntity.created(uri).body(project);
    }

    @PostMapping("/{id}/task")
    public ResponseEntity<TaskOutputDTO> addTask(@RequestBody TaskInputDTO dto, @PathVariable String id){
        var task = taskService.addTask(dto, id);
        URI uri = URI.create("/projects/" + id + "/" + task.id().toString());
        return ResponseEntity.created(uri).body(task);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProjectFullOutputDTO> editProject(@PathVariable String id){

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/member/{memberId}")
    public ResponseEntity<?> addMember(@PathVariable String id, @PathVariable String memberId){
        projectService.addMember(id, memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/completed")
    public ResponseEntity<?> setProjectCompleted(@PathVariable String id){

        projectService.setProjectCompleted(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/review")
    public ResponseEntity<?> setProjectUnderReview(@PathVariable String id){

        projectService.setProjectUnderReview(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/public")
    public ResponseEntity<?> setProjectPublic(@PathVariable String id){

        projectService.setProjectPublic(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/private")
    public ResponseEntity<?> setProjectPrivate(@PathVariable String id){
        projectService.setProjectPrivate(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id){

        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

}
