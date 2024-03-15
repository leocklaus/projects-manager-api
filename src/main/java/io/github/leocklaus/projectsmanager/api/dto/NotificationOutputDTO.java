package io.github.leocklaus.projectsmanager.api.dto;

import java.util.UUID;

public record NotificationOutputDTO(UUID actorId, String actorName, String message) {
}
