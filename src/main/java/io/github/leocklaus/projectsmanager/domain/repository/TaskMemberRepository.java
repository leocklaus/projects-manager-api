package io.github.leocklaus.projectsmanager.domain.repository;

import io.github.leocklaus.projectsmanager.domain.model.TaskMember;
import io.github.leocklaus.projectsmanager.domain.model.TaskMemberKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskMemberRepository extends JpaRepository<TaskMember, TaskMemberKey> {
}
