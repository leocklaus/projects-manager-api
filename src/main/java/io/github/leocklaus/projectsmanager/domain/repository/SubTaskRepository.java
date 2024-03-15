package io.github.leocklaus.projectsmanager.domain.repository;

import io.github.leocklaus.projectsmanager.domain.model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubTaskRepository extends JpaRepository<SubTask, UUID> {
}
