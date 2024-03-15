package io.github.leocklaus.projectsmanager.domain.service.strategy;

import io.github.leocklaus.projectsmanager.domain.exception.ProjectNotFoundException;
import io.github.leocklaus.projectsmanager.domain.exception.TaskNotFoundException;
import io.github.leocklaus.projectsmanager.domain.model.NotificationType;
import io.github.leocklaus.projectsmanager.domain.model.ProjectMember;
import io.github.leocklaus.projectsmanager.domain.model.User;
import io.github.leocklaus.projectsmanager.domain.repository.ProjectRepository;
import io.github.leocklaus.projectsmanager.domain.repository.TaskRepository;
import io.github.leocklaus.projectsmanager.domain.service.NotificationService;
import io.github.leocklaus.projectsmanager.domain.service.NotificationStrategy;
import io.github.leocklaus.projectsmanager.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TaskCompletedNotificationStrategy implements NotificationStrategy {

    @Autowired
    private  NotificationService notificationService;
    @Autowired
    private  UserService userService;
    @Autowired
    private TaskRepository taskRepository;


    public TaskCompletedNotificationStrategy(){}

    @Override
    public void sendNotification(User actor, UUID objectId) {

        var task = taskRepository.findById(objectId)
                .orElseThrow(()-> new TaskNotFoundException(objectId));

        List<User> members = task.getTaskMembers().stream()
                .map(taskMember -> taskMember.getProjectMember().getUser()).toList();

        notificationService.postNotification(actor, members, NotificationType.TASK_COMPLETED);
    }
}
