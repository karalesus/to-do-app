import React, {useState} from "react";
import {addTask} from "../api/ApiClient.js";
import ErrorMessage from "./ErrorMessage.jsx";
import styles from "../styles/TaskForm.module.css"

export default function TaskForm({onTaskAdded}) {
    const [newTask, setNewTask] = useState({
        title: "",
        description: "",
        priority: "MEDIUM",
    });

    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const {name, value} = e.target;
        setNewTask((prev) => ({...prev, [name]: value}));
        setErrors((prev) => ({...prev, [name]: ''}))
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
       setErrors({});
        try {
            const task = await addTask(newTask);
            setNewTask({title: "", description: "", priority: "MEDIUM"});
            setErrors({});
            onTaskAdded(task);
        } catch (error) {
            setErrors(error.response?.data);
        }
    };

    return (
        <form onSubmit={handleSubmit} className={styles.taskForm}>
            <h2>Новая задача</h2>
            <input
                id="title"
                type="text"
                name="title"
                value={newTask.title}
                onChange={handleChange}
                placeholder="Название задачи"
                className={errors.title ? styles.errorInput : styles.input}
            />
            {errors.title && <ErrorMessage message={errors.title}/>}
            <textarea
                id="description"
                name="description"
                value={newTask.description}
                onChange={handleChange}
                placeholder="Описание"
                className={errors.description ? styles.errorInput : styles.input}
            />
            {errors.description && (
                <ErrorMessage message={errors.description}/>
            )}
            <select
                id="priority"
                name="priority"
                value={newTask.priority}
                onChange={handleChange}
                className={styles.select}
            >
                <option value="" disabled>
                    Выберите приоритет задачи
                </option>
                <option value="HIGH">Высокий</option>
                <option value="MEDIUM">Средний</option>
                <option value="LOW">Низкий</option>
            </select>
            <button type="submit" className={styles.submitButton}>
                Добавить
            </button>
        </form>

    );
}
