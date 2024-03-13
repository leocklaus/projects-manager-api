package io.github.leocklaus.projectsmanager.api.controller;

import io.github.leocklaus.projectsmanager.api.dto.ProjectFullOutputDTO;
import io.github.leocklaus.projectsmanager.api.dto.ProjectInputDTO;
import io.github.leocklaus.projectsmanager.api.dto.ProjectShortOutputDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @GetMapping
    public ResponseEntity<Page<ProjectShortOutputDTO>> getAllProjectsPaged(Pageable pageable){

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectFullOutputDTO> getProjectById(@PathVariable String id){


        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> addProject(@Valid @RequestBody ProjectInputDTO dto){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectFullOutputDTO> editProject(@PathVariable String id){

        URI uri = URI.create("/projects/id");

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}/completed")
    public ResponseEntity<?> setProjectCompleted(){
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/review")
    public ResponseEntity<?> setProjectUnderReview(){
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/public")
    public ResponseEntity<?> setProjectPublic(){
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/private")
    public ResponseEntity<?> setProjectPrivate(){
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/members")
    public ResponseEntity<?> addMember(){
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(){
        return ResponseEntity.noContent().build();
    }

}
