import styles from "../styles/FinishedTasks.module.css"
import {useState} from "react";
import {handleReturn} from "../api/ApiClient.js";
import ErrorMessage from "./ErrorMessage.jsx";

export default function FinishedTasks({tasks, setTasks}) {
    const [error, setError] = useState(null);

    const onReturnClick = async (id) => {
        try {
            await handleReturn(id);
            setError(null)
            setTasks(prev =>
                prev.map(t =>
                    t.id === id ? { ...t, status: "ACTIVE" } : t
                )
            );
        } catch (error) {
            setError("Не удалось вернуть задачу" + error);
        }
    };

    const finishedTasks = tasks.filter((task) => task.status === "FINISHED");

    return (
        <div className={styles.finishedTasks}>
            <h2>Выполнено</h2>
            {error && <ErrorMessage message={error}/>}
            <ul className={styles.taskItem}>
                {finishedTasks.map((task) => (
                    <li
                        key={task.id}>
                        <button className={styles.returnButton}
                                onClick={() => onReturnClick(task.id)}>←
                        </button>
                        <span>{task.title}</span>
                    </li>
                ))}
            </ul>
        </div>
    );
}
