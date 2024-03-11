package io.github.leocklaus.projectsmanager.domain.exception;

public class UserNotFoundException extends UserException{
    public UserNotFoundException(String id) {
        super("User not found with id: " + id);
    }

}
