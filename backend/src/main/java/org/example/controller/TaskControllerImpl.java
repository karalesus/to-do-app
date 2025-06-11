package org.example.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.TaskInputDTO;
import org.example.dto.TaskOutputDTO;
import org.example.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tasks")
public class TaskControllerImpl implements TaskController {

    private TaskServiceImpl taskService;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    public void setTaskService(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskOutputDTO> getAllTasks() {
        LOG.log(Level.INFO, "Getting all tasks");
        return taskService.getAllTasks();
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<TaskOutputDTO> addTask(@Valid @RequestBody TaskInputDTO form) {
        LOG.log(Level.INFO, "Start adding task");
        var task = taskService.addTask(form);
        LOG.log(Level.INFO, "Task successfully added: " + task);
        return ResponseEntity.ok(task);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") String taskId) {
        String task = taskService.deleteTask(taskId);
        LOG.log(Level.INFO, "Task successfully deleted: " + task);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @Override
    @PutMapping("/{id}/complete")
    public ResponseEntity<String> completeTask(@PathVariable("id")  String taskId) {
        String task = taskService.completeTask(taskId);
        LOG.log(Level.INFO, "Task successfully completed: " + task);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @Override
    @PutMapping("/{id}/return")
    public ResponseEntity<String> returnTask(@PathVariable("id") String taskId) {
        String task = taskService.returnTask(taskId);
        LOG.log(Level.INFO, "Task successfully returned: " + task);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }
}
