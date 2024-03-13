package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.ProjectStatus;
import io.github.leocklaus.projectsmanager.domain.model.ProjectVisibility;

import java.time.LocalDate;
import java.util.List;

public record ProjectFullOutputDTO(String id, String name, String description, LocalDate deadline,
                                   ProjectStatus status, ProjectVisibility visibility, String cover,
                                   UserShortOutputDTO leader, List<UserShortOutputDTO> members,
                                   List<TaskShortOutputDTO> tasks) {
}
