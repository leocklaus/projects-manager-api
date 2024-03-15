package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.Project;
import io.github.leocklaus.projectsmanager.domain.model.ProjectStatus;
import io.github.leocklaus.projectsmanager.domain.model.ProjectVisibility;

import java.time.LocalDate;
import java.util.List;

public record ProjectFullOutputDTO(String id, String name, String description, LocalDate deadline,
                                   ProjectStatus status, ProjectVisibility visibility, String cover,
                                   List<UserShortOutputDTO> members,
                                   List<TaskShortOutputDTO> tasks) {

    public ProjectFullOutputDTO(Project project, List<UserShortOutputDTO> members, List<TaskShortOutputDTO> tasks){
        this(project.getId().toString(), project.getName(), project.getDescription(), project.getDeadline(),
                project.getProjectStatus(), project.getProjectVisibility(), project.getCover(),
                members, tasks);
    }
}
