package org.example.dto;

import org.example.models.Priority;
import org.example.models.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskOutputDTO {
    private String id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Priority priority;
    private Status status;

    public TaskOutputDTO(String id, String title, String description, LocalDateTime createdAt, Priority priority, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.priority = priority;
        this.status = status;
    }

    protected TaskOutputDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
