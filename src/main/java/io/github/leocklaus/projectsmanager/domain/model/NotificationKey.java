package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
public class NotificationKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UUID userId;
    @ManyToOne
    @JoinColumn(name = "notification_object_id")
    private UUID notificationObjectId;

}
