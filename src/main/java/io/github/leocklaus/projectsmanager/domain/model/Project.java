package io.github.leocklaus.projectsmanager.domain.model;

import io.github.leocklaus.projectsmanager.api.dto.ProjectInputDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
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
    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();

    public Project(ProjectInputDTO dto){
        this.name = dto.name();
        this.description = dto.description();
        this.deadline = dto.deadLine();
        this.projectVisibility = dto.visibility();
        this.projectStatus = ProjectStatus.IN_PROGRESS;
    }

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

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void addMember(ProjectMember member){
        members.add(member);
    }
}
