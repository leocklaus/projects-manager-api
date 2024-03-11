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
public class TaskMemberKey implements Serializable {

    @Column(name = "member_id")
    private ProjectMemberKey memberId;
    @Column(name = "task_id")
    private UUID taskId;

}
