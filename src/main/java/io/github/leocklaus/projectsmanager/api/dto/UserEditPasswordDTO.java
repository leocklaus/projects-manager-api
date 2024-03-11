package io.github.leocklaus.projectsmanager.api.dto;

import jakarta.validation.constraints.NotNull;

public record UserEditPasswordDTO(@NotNull String password) {
}
