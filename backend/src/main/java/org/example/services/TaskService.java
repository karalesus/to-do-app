package org.example.services;

import org.example.dto.FinishedTaskOutputDTO;
import org.example.dto.TaskInputDTO;
import org.example.dto.TaskOutputDTO;

import java.util.List;

public interface TaskService {
    TaskOutputDTO addTask(TaskInputDTO taskInputDTO);
    String deleteTask(String taskId);
    String completeTask(String taskId);

    List<TaskOutputDTO> getActiveTasks();
    List<FinishedTaskOutputDTO> getFinishedTasks();
    List<TaskOutputDTO> getDeletedTasks();

    List<TaskOutputDTO> getAllTasks();
    String returnTask(String taskId);
}
