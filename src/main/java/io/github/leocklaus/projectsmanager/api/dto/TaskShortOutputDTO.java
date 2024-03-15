package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.Task;

import java.util.UUID;

public record TaskShortOutputDTO(UUID id, String title, String cover, Integer numberOfMember,
                                 Integer numberOfSubtasks, Long numberOfCompletedSubtasks) {
    public TaskShortOutputDTO(Task task, Integer numberOfSubtasks, Long numberOfCompletedSubtasks,
                              Integer numberOfMembers){
        this(task.getId(), task.getTitle(), task.getCover(), numberOfMembers, numberOfSubtasks, numberOfCompletedSubtasks);
    }
}
