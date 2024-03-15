package io.github.leocklaus.projectsmanager.domain.model;

import io.github.leocklaus.projectsmanager.api.dto.SubTaskInputDTO;
import io.github.leocklaus.projectsmanager.api.dto.TaskInputDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubTask extends BaseEntity {

    @Column(nullable = false)
    private String title;
    private String description;
    private LocalDateTime due;
    private boolean isChecked;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public SubTask(SubTaskInputDTO dto){
        this.title = dto.title();
        this.description = dto.description();
        this.due = dto.due();
        this.isChecked = false;
    }

    public void setChecked(){
        this.isChecked = true;
    }
    public void setUnchecked(){
        this.isChecked = false;
    }

}
