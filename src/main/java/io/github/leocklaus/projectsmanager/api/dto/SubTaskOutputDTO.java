package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.SubTask;

import java.time.LocalDateTime;
import java.util.UUID;

public record SubTaskOutputDTO(UUID id, String title, String description, LocalDateTime due, boolean isChecked){
    public SubTaskOutputDTO(SubTask subTask){
        this(subTask.getId(), subTask.getTitle(), subTask.getDescription(), subTask.getDue(), subTask.isChecked());
    }
}
