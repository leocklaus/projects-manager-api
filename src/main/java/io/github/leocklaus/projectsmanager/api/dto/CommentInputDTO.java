package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.CommentType;

import java.util.UUID;

public record CommentInputDTO(UUID userId, String content, UUID rootCommentId, CommentType type) {

}
