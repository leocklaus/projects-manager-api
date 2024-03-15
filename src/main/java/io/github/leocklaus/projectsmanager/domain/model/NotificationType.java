package io.github.leocklaus.projectsmanager.domain.model;

public enum NotificationType {
    NEW_COMMENT("adicionou um novo comentário!"),
    NEW_REPLY("respondeu ao seu comentário"),
    PROJECT_CREATED("criou um projeto"),
    ADDED_TO_PROJECT("adicionou você ao projeto"),
    NEW_TASK_CREATED("adicionou uma niva tarefa"),
    NEW_SUBTASK_CREATED("adicionou uma nova subtarefa"),
    PROJECT_COMPLETED("conclui o projeto"),
    TASK_COMPLETED("conclui a tarefa"),
    SUBTASK_COMPLETED("conclui a subtarefa");

    private final String message;

    NotificationType(String message) {
        this.message = message;
    }

    public String getMessage(String actorName){
        return actorName + " " + this.message;
    }
}
