package io.github.leocklaus.projectsmanager.api.dto;

import java.time.LocalDate;
import java.util.UUID;

public record TaskInputDTO(UUID id, String title, String description, LocalDate due, String leaderId) {
}
