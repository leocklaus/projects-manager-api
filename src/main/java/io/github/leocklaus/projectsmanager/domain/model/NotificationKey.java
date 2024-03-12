package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
public class NotificationKey implements Serializable {

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "notification_object_id")
    private UUID notificationObjectId;

}
