package io.github.leocklaus.projectsmanager.api.dto;

import java.util.UUID;

public record TaskShortOutputDTO(UUID id, String title, String cover, Integer numberOfMember,
                                 Integer numberOfTasks, Integer numberOfCompletedTasks) {
}
