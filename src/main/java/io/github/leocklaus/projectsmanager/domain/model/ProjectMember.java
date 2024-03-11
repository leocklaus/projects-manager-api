package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectMember {

    @EmbeddedId
    private ProjectMemberKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    private MemberType memberType;

    public void setProjectLeader(){
        this.memberType = MemberType.LEADER;
    }
}
