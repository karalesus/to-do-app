package org.example.repositories;

import org.example.dto.FinishedTaskOutputDTO;
import org.example.dto.TaskOutputDTO;
import org.example.models.Status;
import org.example.models.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> findTasksByStatus(Status status);

}
