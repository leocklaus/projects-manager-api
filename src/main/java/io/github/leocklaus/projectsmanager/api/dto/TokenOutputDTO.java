package io.github.leocklaus.projectsmanager.api.dto;

import java.time.Instant;

public record TokenOutputDTO(String token, Instant expiresAt) {
}
