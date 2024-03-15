package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.SubTask;
import io.github.leocklaus.projectsmanager.domain.model.Task;
import io.github.leocklaus.projectsmanager.domain.model.TaskMember;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record TaskOutputDTO(UUID id, String title, String cover, Set<UserShortOutputDTO> members, List<SubTask> subTasks) {
    public TaskOutputDTO(Task task){
        this(task.getId(), task.getTitle(), task.getCover(), task.getTaskMembers().stream().map(UserShortOutputDTO::new).collect(Collectors.toSet()), task.getSubTasks());
    }
}
