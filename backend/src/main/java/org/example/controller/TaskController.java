package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.TaskInputDTO;
import org.example.dto.TaskOutputDTO;
import org.example.input.AddTaskForm;
import org.example.viewModels.BaseViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/tasks")
public interface TaskController {

    @PostMapping("/add")
    ResponseEntity<TaskOutputDTO> addTask(@Valid @RequestBody TaskInputDTO form);

    @PutMapping("/{id}")
    ResponseEntity<String> deleteTask(@PathVariable("id") String taskId);

    @PutMapping("/{id}/complete")
    ResponseEntity<String> completeTask(@PathVariable("id") String taskId);

    @PutMapping("/{id}/return")
    ResponseEntity<String> returnTask(@PathVariable("id") String taskId);
}
