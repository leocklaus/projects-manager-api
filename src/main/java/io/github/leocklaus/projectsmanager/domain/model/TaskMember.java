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
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    })
    private ProjectMember projectMember;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    private Task task;

    private MemberType memberType;

}
