package io.github.leocklaus.projectsmanager.domain.repository;

import io.github.leocklaus.projectsmanager.domain.model.Comment;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@ReadingConverter
public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
