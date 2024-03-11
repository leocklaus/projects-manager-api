package io.github.leocklaus.projectsmanager.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    private LocalDate deadline;
    private String cover;
    @Column(nullable = false)
    private ProjectStatus projectStatus;
    @Column(nullable = false)
    private ProjectVisibility projectVisibility;
    @OneToMany(mappedBy = "project")
    private Set<ProjectMember> members = new HashSet<>();

    public void setCompleted(){
        this.projectStatus = ProjectStatus.COMPLETED;
    }

    public void setUnderReview(){
        this.projectStatus = ProjectStatus.REVISION;
    }

    public void setPublic(){
        this.projectVisibility = ProjectVisibility.PUBLIC;
    }

    public void setPrivate(){
        this.projectVisibility = ProjectVisibility.PRIVATE;
    }

    public void addMember(User member, MemberType memberType){
        members.add(new ProjectMember(new ProjectMemberKey(member.getId(),
                getId()), member, this, memberType));
    }
}
