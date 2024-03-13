package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.User;

import java.util.UUID;

public record UserShortOutputDTO(UUID id, String name) {
    public UserShortOutputDTO(User user){
        this(user.getId(), user.getName());
    }
}
