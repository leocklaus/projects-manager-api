package io.github.leocklaus.projectsmanager.domain.projection;

import java.util.UUID;

public interface ProjectTasksProjection {

    UUID getId();
    String getTitle();
    String getCover();
    Long getNumberOfSubtasks();
    Long getNumberOfMembers();
    Long getNumberOfSubtasksChecked();
}
