package io.github.leocklaus.projectsmanager.domain.service;

import io.github.leocklaus.projectsmanager.domain.model.User;

import java.util.UUID;

public interface NotificationStrategy {

    public void sendNotification(User actor, UUID objectId);

}
