package io.github.leocklaus.projectsmanager.domain.model;

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

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task extends BaseEntity{
    private String title;
    private String description;
    private String cover;
    private LocalDate due;
    private TaskStatus taskStatus;
    @OneToMany(mappedBy = "task")
    private Set<TaskMember> taskMembers = new HashSet<>();
    @OneToMany(mappedBy = "task")
    private List<SubTask> subTasks = new ArrayList<>();
    @OneToMany(mappedBy = "task")
    private List<Comment> comments = new ArrayList<>();

    public void setInProgress(){
        this.taskStatus = TaskStatus.IN_PROGRESS;
    }

    public void setUnderRevision(){
        this.taskStatus = TaskStatus.REVISION;
    }
    public void setCompleted(){
        this.taskStatus = TaskStatus.COMPLETED;
    }

    public void addMember(ProjectMember projectMember, MemberType type){
        this.taskMembers.add(new TaskMember(new TaskMemberKey(projectMember.getId(), this.getId()),
                projectMember, this, type));
    }

    public void addSubTask(SubTask subTask){
        this.subTasks.add(subTask);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }
}
