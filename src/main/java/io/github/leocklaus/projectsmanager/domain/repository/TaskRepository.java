package io.github.leocklaus.projectsmanager.domain.repository;

import io.github.leocklaus.projectsmanager.domain.model.Project;
import io.github.leocklaus.projectsmanager.domain.model.Task;
import io.github.leocklaus.projectsmanager.domain.projection.ProjectTasksProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(value = "SELECT t.id as id, t.title as title, t.cover as cover,\n" +
            "(select count(*)\n" +
            "from SubTask s\n" +
            "where t = s.task\n" +
            ") as numberOfSubtasks,\n" +
            "(select count(*)\n" +
            "from TaskMember m\n" +
            "where t = m.task\n" +
            ") as numberOfMembers,\n" +
            "(select count(*)\n" +
            "from SubTask as st\n" +
            "where t = st.task and st.isChecked = true\n" +
            ") as numberOfSubtasksChecked\n" +
            "from Task t\n" +
            "where t.project = :project")
    List<ProjectTasksProjection> getProjectTasksWithDetails(@Param("project") Project project);

}
