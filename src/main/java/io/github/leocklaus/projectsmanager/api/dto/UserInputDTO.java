package io.github.leocklaus.projectsmanager.api.dto;

import jakarta.validation.constraints.NotNull;

public record UserInputDTO(@NotNull String name, @NotNull String email,
                           @NotNull String phone, @NotNull String password) {
}
