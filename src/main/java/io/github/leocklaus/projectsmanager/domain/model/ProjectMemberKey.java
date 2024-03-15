package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberKey implements Serializable {
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "project_id")
    private UUID projectId;

}
