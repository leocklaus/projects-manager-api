package io.github.leocklaus.projectsmanager.domain.repository;

import io.github.leocklaus.projectsmanager.domain.model.Project;
import io.github.leocklaus.projectsmanager.domain.model.ProjectMember;
import io.github.leocklaus.projectsmanager.domain.model.ProjectMemberKey;
import io.github.leocklaus.projectsmanager.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberKey> {
    Optional<ProjectMember> findByUserAndProject(User user, Project project);
}
