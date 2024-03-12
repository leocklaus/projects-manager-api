package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationObject extends BaseEntity{
    private User actor;
    private NotificationType notificationType;

    public NotificationObject(UUID id, User actor, NotificationType notificationType) {
        super(id);
        this.actor = actor;
        this.notificationType = notificationType;
    }
}
