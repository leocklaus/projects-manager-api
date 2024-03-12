package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationObject extends BaseEntity{
    private User actor;
    private NotificationType notificationType;
}
