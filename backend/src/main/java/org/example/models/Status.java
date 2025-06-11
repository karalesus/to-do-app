package org.example.models;

public enum Status {

    ACTIVE("Активно"),
    DELETED("Удалено"),
    FINISHED("Выполнено");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
