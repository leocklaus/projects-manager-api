package io.github.leocklaus.projectsmanager.domain.service.strategy;

import io.github.leocklaus.projectsmanager.domain.exception.CommentNotFoundException;
import io.github.leocklaus.projectsmanager.domain.model.NotificationType;
import io.github.leocklaus.projectsmanager.domain.model.TaskMember;
import io.github.leocklaus.projectsmanager.domain.model.User;
import io.github.leocklaus.projectsmanager.domain.repository.CommentRepository;
import io.github.leocklaus.projectsmanager.domain.service.NotificationService;
import io.github.leocklaus.projectsmanager.domain.service.NotificationStrategy;
import io.github.leocklaus.projectsmanager.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class NewCommentNotificationStrategy implements NotificationStrategy {

    @Autowired
    private  NotificationService notificationService;
    @Autowired
    private  UserService userService;
    @Autowired
    private  CommentRepository commentRepository;


    public NewCommentNotificationStrategy(){}

    @Override
    public void sendNotification(User actor, UUID objectId) {

        var comment = commentRepository.findById(objectId)
                        .orElseThrow(CommentNotFoundException::new);

        List<User> members = comment.getTask().getTaskMembers()
                .stream().map(taskMember -> {
                    return taskMember.getProjectMember().getUser();
                }).toList();

        notificationService.postNotification(actor, members, NotificationType.NEW_COMMENT);
    }
}
