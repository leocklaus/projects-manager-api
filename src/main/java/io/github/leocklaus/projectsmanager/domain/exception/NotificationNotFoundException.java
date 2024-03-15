package io.github.leocklaus.projectsmanager.domain.exception;

import java.util.UUID;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(){
        super("Notification not found");
    }
}
