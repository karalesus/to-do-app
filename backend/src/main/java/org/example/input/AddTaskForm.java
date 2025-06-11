package org.example.input;

import jakarta.validation.constraints.*;
import org.example.models.Priority;

public record AddTaskForm(

        @NotBlank(message = "Имя не может быть пустым")
        @Size(min = 5, max = 127, message = "Имя должно содержать минимум 5 символов")
        String title,

        @Size(min = 5, max = 255, message = "Описание должно содержать не менее 5 и не более 255 символов")
        String description,

        @NotBlank(message = "Выберите приоритет задания!")
        Priority priority
) {
}
