package org.example.services;

import jakarta.validation.ConstraintViolation;
import org.example.dto.FinishedTaskOutputDTO;
import org.example.dto.TaskInputDTO;
import org.example.dto.TaskOutputDTO;
import org.example.exceptions.InvalidTaskDataException;
import org.example.models.Status;
import org.example.models.Task;
import org.example.repositories.TaskRepositoryImpl;
import org.example.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    private TaskRepositoryImpl taskRepository;

    @Autowired
    public TaskServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setTaskRepository(TaskRepositoryImpl taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskOutputDTO addTask(TaskInputDTO taskInputDTO) {
        validateTask(taskInputDTO);
        Task task = convertTaskDtoToTask(taskInputDTO);
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(Status.ACTIVE);
        Task savedTask = taskRepository.save(task);

        return convertTaskToTaskDTO(savedTask);
    }

    @Override
    public String deleteTask(String taskId) {
        Task existingTask = taskRepository.findById(UUID.fromString(taskId)).orElseThrow(() -> new IllegalArgumentException("Задание с ID " + taskId + " не найдено"));
        existingTask.setStatus(Status.DELETED);
        taskRepository.save(existingTask);
        return existingTask.getId().toString();
    }

    @Override
    public String returnTask(String taskId) {
        Task existingTask = taskRepository.findById(UUID.fromString(taskId)).orElseThrow(() -> new IllegalArgumentException("Задание с ID " + taskId + " не найдено"));
        existingTask.setStatus(Status.ACTIVE);
        taskRepository.save(existingTask);
        return existingTask.getId().toString();
    }

    @Override
    public String completeTask(String taskId) {
        Task existingTask = taskRepository.findById(UUID.fromString(taskId)).orElseThrow(() -> new IllegalArgumentException("Задание с ID " + taskId + " не найдено"));
        if (existingTask.getStatus() == Status.ACTIVE) {
            existingTask.setStatus(Status.FINISHED);
            taskRepository.save(existingTask);
            return existingTask.getId().toString();
        } else {
            throw new InvalidTaskDataException("Это задание уже выполнено или удалено!");
        }
    }

    @Override
    public List<TaskOutputDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::convertTaskToTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskOutputDTO> getActiveTasks() {
        return taskRepository.findTasksByStatus(Status.ACTIVE)
                .stream()
                .map(this::convertTaskToTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FinishedTaskOutputDTO> getFinishedTasks() {
        return taskRepository.findTasksByStatus(Status.FINISHED)
                .stream()
                .map(this::convertTaskToFinishedTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskOutputDTO> getDeletedTasks() {
        return taskRepository.findTasksByStatus(Status.DELETED)
                .stream()
                .map(this::convertTaskToTaskDTO)
                .collect(Collectors.toList());
    }

    private void validateTask(TaskInputDTO taskInputDTO) {
        if (!this.validationUtil.isValid(taskInputDTO)) {
            String constraintViolations = this.validationUtil
                    .violations(taskInputDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            throw new InvalidTaskDataException(constraintViolations);
        }
    }

    public Task convertTaskDtoToTask(TaskInputDTO taskInputDTO) {
        return modelMapper.map(taskInputDTO, Task.class);
    }

    public TaskOutputDTO convertTaskToTaskDTO(Task task) {
        return modelMapper.map(task, TaskOutputDTO.class);
    }

    public FinishedTaskOutputDTO convertTaskToFinishedTaskDTO(Task task) {
        return modelMapper.map(task, FinishedTaskOutputDTO.class);
    }
}
