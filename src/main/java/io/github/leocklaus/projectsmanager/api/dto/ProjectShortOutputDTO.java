package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.Project;
import io.github.leocklaus.projectsmanager.domain.model.ProjectStatus;
import io.github.leocklaus.projectsmanager.domain.model.ProjectVisibility;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectShortOutputDTO(
        UUID id, String name, String description, LocalDate deadline, ProjectStatus projectStatus,
        ProjectVisibility projectVisibility) {

    public ProjectShortOutputDTO(Project project){
        this(project.getId(),project.getName(), project.getDescription(), project.getDeadline(),
                project.getProjectStatus(), project.getProjectVisibility());
    }
}
