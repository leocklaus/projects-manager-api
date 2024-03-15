package io.github.leocklaus.projectsmanager.domain.repository;

import io.github.leocklaus.projectsmanager.domain.model.Notification;
import io.github.leocklaus.projectsmanager.domain.model.NotificationKey;
import io.github.leocklaus.projectsmanager.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, NotificationKey> {
    Page<Notification> findAllByUser(Pageable pageable, User user);

}
