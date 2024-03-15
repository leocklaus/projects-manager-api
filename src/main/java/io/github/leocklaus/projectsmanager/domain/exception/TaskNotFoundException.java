package io.github.leocklaus.projectsmanager.domain.exception;

import java.util.UUID;

public class TaskNotFoundException extends TaskException{
    public TaskNotFoundException(String msg) {
        super(msg);
    }

    public TaskNotFoundException(UUID id){
        this("Task not found with id: " + id.toString());
    }
}
