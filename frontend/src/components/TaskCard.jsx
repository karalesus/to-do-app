import {handleDelete, handleComplete} from "../api/ApiClient.js";
import styles from '../styles/ActiveTasks.module.css';
import {PRIORITY_LABELS} from "../constants/constants.js";
import {formatDate} from "../utils/utils.js";
import {useState} from "react";
import ErrorMessage from "./ErrorMessage.jsx";

export default function TaskCard({task, setTasks}) {
    const [error, setError] = useState("");
    const onDeleteClick = async (task) => {
        try {
            await handleDelete(task.id);
            setTasks(prev =>
                prev.map(t =>
                    t.id === task.id ? {...t, status: "DELETED"} : t
                )
            );
        } catch (error) {
            setError("Не удалось удалить задачу: ", error);
        }
    };

    const onCompleteClick = async (task) => {
        try {
            await handleComplete(task.id);
            setTasks(prev =>
                prev.map(t =>
                    t.id === task.id ? {...t, status: "FINISHED"} : t
                )
            );
        } catch (error) {
            setError("Не удалось отметить задачу как выполненную: ", error);
        }
    };

    return (
        <div className={styles.taskCard}>
            {error && <ErrorMessage message={error}/>}
            <h3>{task.title}</h3>
            <p>{task.description}</p>
            <p>Создана: {formatDate(task.createdAt)}</p>
            <p>Приоритет: {PRIORITY_LABELS[task.priority]}</p>
            <div className={styles.buttonGroup}>
                <button className={styles.deleteButton} onClick={() => onDeleteClick(task)}>
                    Удалить
                </button>
                <button className={styles.completeButton} onClick={() => onCompleteClick(task)}>
                    Выполнено
                </button>
            </div>
        </div>
    );
};
