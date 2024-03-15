package io.github.leocklaus.projectsmanager.domain.repository;

import io.github.leocklaus.projectsmanager.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
