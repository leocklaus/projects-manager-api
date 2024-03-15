package io.github.leocklaus.projectsmanager.api.dto;

import io.github.leocklaus.projectsmanager.domain.model.MemberType;
import io.github.leocklaus.projectsmanager.domain.model.TaskMember;
import io.github.leocklaus.projectsmanager.domain.model.User;

import java.util.UUID;

public record UserShortOutputDTO(UUID id, String name, MemberType type) {

    public UserShortOutputDTO(TaskMember member){
        this(member.getProjectMember().getUser().getId(), member.getProjectMember().getUser().getName(),
                member.getMemberType());
    }

}
