package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.User;

public record UserOutputDTO(String UUID, String name, String email, String phone) {
    public UserOutputDTO(User user){
        this(user.getId().toString(), user.getName(), user.getEmail(), user.getPhone());
    }
}
