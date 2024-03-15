package io.github.leocklaus.projectsmanager.domain.service;

import io.github.leocklaus.projectsmanager.api.dto.NotificationOutputDTO;
import io.github.leocklaus.projectsmanager.domain.exception.NotificationNotFoundException;
import io.github.leocklaus.projectsmanager.domain.model.*;
import io.github.leocklaus.projectsmanager.domain.repository.NotificationObjectRepository;
import io.github.leocklaus.projectsmanager.domain.repository.NotificationRepository;
import io.github.leocklaus.projectsmanager.domain.service.strategy.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationObjectRepository notificationObjectRepository;
    private final NotificationRepository notificationRepository;
    private final UserService userService;

    private final Map<NotificationType, NotificationStrategy> mapStrategy = Map.of(
            NotificationType.NEW_COMMENT, new NewCommentNotificationStrategy(),
            NotificationType.NEW_REPLY, new NewReplyNotificationStrategy(),
            NotificationType.NEW_TASK_CREATED, new TaskCreatedNotificationStrategy(),
            NotificationType.ADDED_TO_PROJECT, new AddedToProjectNotificationStrategy(),
            NotificationType.PROJECT_CREATED, new ProjectCreatedNotificationStrategy(),
            NotificationType.NEW_SUBTASK_CREATED, new SubTaskCreatedNotificationStrategy(),
            NotificationType.PROJECT_COMPLETED, new ProjectCompletedNotificationStrategy(),
            NotificationType.TASK_COMPLETED, new TaskCompletedNotificationStrategy(),
            NotificationType.SUBTASK_COMPLETED, new SubTaskCompletedNotificationStrategy()

    );

    public void notify(User actor, UUID objectId, NotificationType type){
        mapStrategy.get(type).sendNotification(actor, objectId);
    }

    public NotificationService(NotificationObjectRepository notificationObjectRepository, NotificationRepository notificationRepository, UserService userService) {
        this.notificationObjectRepository = notificationObjectRepository;
        this.notificationRepository = notificationRepository;
        this.userService = userService;
    }

    @Transactional
    public void postNotification(User actor, List<User> notifiers, NotificationType type){
        var notificationObject = createNotificationObject(actor, type);
        notifiers.forEach(notifier->{
            var notification = createNotification(notifier, notificationObject);
            notificationRepository.save(notification);
        });
    }

    @Transactional
    public void setNotificationRead(NotificationKey id){
        var notification = getNotificationByIdOrThrowsExceptionIfNotExists(id);
        notification.setRead();
        notificationRepository.save(notification);
    }

    public NotificationOutputDTO getNotificationById(NotificationKey id){
        var notification = getNotificationByIdOrThrowsExceptionIfNotExists(id);
        return createNotificationDTO(notification);
    }

    public Page<NotificationOutputDTO> getNotificationsByUser(Pageable pageable, String userId){
        var user = userService.getUserByIdOrThrowsExceptionIfNotExists(userId);
        var notifications = notificationRepository.findAllByUser(pageable, user);
        return notifications.map(this::createNotificationDTO);
    }

    public NotificationObject createNotificationObject(User actor, NotificationType type){
        var notification = new NotificationObject(actor, type);
        notificationObjectRepository.save(notification);
        return notification;
    }

    public Notification createNotification(User notifier, NotificationObject notificationObject){
        return new Notification(
                new NotificationKey(notifier.getId(), notificationObject.getId()),
                notifier, notificationObject,
                false);
    }

    public Notification getNotificationByIdOrThrowsExceptionIfNotExists(NotificationKey id){
        return notificationRepository.findById(id)
                .orElseThrow(NotificationNotFoundException::new);

    }

    public NotificationOutputDTO createNotificationDTO(Notification notification){
        var notificationObject = notification.getNotificationObject();
        var actor = notificationObject.getActor();
        return new NotificationOutputDTO(
                actor.getId(),
                actor.getName(),
                notificationObject.getNotificationType().getMessage(actor.getName())
        );
    }


}
