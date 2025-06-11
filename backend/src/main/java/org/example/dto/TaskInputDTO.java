package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.models.Priority;
public class TaskInputDTO {

    private String title;
    private String description;
    private Priority priority;

    protected TaskInputDTO() {
    }

    public TaskInputDTO(String title, String description, Priority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 1, max = 32, message = "Имя должно содержать от 1 до 32 символов")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Size(max = 255, message = "Описание должно содержать не более 255 символов")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
