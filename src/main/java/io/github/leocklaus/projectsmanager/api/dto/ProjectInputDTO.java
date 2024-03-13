package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.ProjectVisibility;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectInputDTO(String name, String description, LocalDate deadLine, ProjectVisibility visibility,
                              UUID leaderId) {
}
