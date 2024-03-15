package io.github.leocklaus.projectsmanager.domain.service.strategy;

import io.github.leocklaus.projectsmanager.domain.exception.CommentNotFoundException;
import io.github.leocklaus.projectsmanager.domain.model.NotificationType;
import io.github.leocklaus.projectsmanager.domain.model.User;
import io.github.leocklaus.projectsmanager.domain.repository.CommentRepository;
import io.github.leocklaus.projectsmanager.domain.service.NotificationService;
import io.github.leocklaus.projectsmanager.domain.service.NotificationStrategy;
import io.github.leocklaus.projectsmanager.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class NewReplyNotificationStrategy implements NotificationStrategy {

    @Autowired
    private  NotificationService notificationService;
    @Autowired
    private  UserService userService;
    @Autowired
    private  CommentRepository commentRepository;


    public NewReplyNotificationStrategy(){}

    @Override
    public void sendNotification(User actor, UUID objectId) {

        var comment = commentRepository.findById(objectId)
                        .orElseThrow(CommentNotFoundException::new);

        User user = comment.getRootComment().getUser();

        List<User> members = List.of(user);

        notificationService.postNotification(actor, members, NotificationType.NEW_REPLY);
    }
}
