package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskMember {

    @EmbeddedId
    private TaskMemberKey id;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private ProjectMember projectMember;

    @ManyToOne
    @MapsId("taskID")
    @JoinColumn(name = "task_id")
    private Task task;

    private MemberType memberType;

}
