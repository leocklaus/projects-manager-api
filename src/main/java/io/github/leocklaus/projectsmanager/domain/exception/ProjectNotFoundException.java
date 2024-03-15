package io.github.leocklaus.projectsmanager.domain.exception;

import java.util.UUID;

public class ProjectNotFoundException extends ProjectException{

    public ProjectNotFoundException(String msg) {
        super(msg);
    }

    public ProjectNotFoundException(UUID id){
        super("Project not found with id: " + id.toString());
    }
}
