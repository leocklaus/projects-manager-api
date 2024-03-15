package io.github.leocklaus.projectsmanager.api.dto;

import java.time.LocalDateTime;

public record SubTaskInputDTO (String title, String description, LocalDateTime due){
}
