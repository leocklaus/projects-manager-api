package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @EmbeddedId
    private NotificationKey id;

    @OneToMany
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @MapsId("notificationObjectId")
    @JoinColumn(name = "notification_object_id")
    private NotificationObject notificationObject;

    private boolean isRead;

    public void setRead(){
        this.isRead = true;
    }
}
