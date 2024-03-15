package io.github.leocklaus.projectsmanager.domain.service.strategy;

import io.github.leocklaus.projectsmanager.domain.exception.CommentNotFoundException;
import io.github.leocklaus.projectsmanager.domain.exception.ProjectNotFoundException;
import io.github.leocklaus.projectsmanager.domain.model.NotificationType;
import io.github.leocklaus.projectsmanager.domain.model.ProjectMember;
import io.github.leocklaus.projectsmanager.domain.model.User;
import io.github.leocklaus.projectsmanager.domain.repository.CommentRepository;
import io.github.leocklaus.projectsmanager.domain.repository.ProjectRepository;
import io.github.leocklaus.projectsmanager.domain.service.NotificationService;
import io.github.leocklaus.projectsmanager.domain.service.NotificationStrategy;
import io.github.leocklaus.projectsmanager.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProjectCreatedNotificationStrategy implements NotificationStrategy {

    @Autowired
    private  NotificationService notificationService;
    @Autowired
    private  UserService userService;
    @Autowired
    private ProjectRepository projectRepository;


    public ProjectCreatedNotificationStrategy(){}

    @Override
    public void sendNotification(User actor, UUID objectId) {

        var project = projectRepository.findById(objectId)
                .orElseThrow(()-> new ProjectNotFoundException(objectId));

        List<User> members = project.getMembers().stream()
                .map(ProjectMember::getUser).toList();

        notificationService.postNotification(actor, members, NotificationType.PROJECT_CREATED);
    }
}
