import styles from '../styles/ActiveTasks.module.css';
import TaskCard from "./TaskCard.jsx";

export default function ActiveTasks ({ tasks, setTasks }) {
    const activeTasks = tasks.filter((task) => task.status === "ACTIVE");

    return (
        <div className={styles.activeTasks}>
            <h2>Надо сделать</h2>
            {activeTasks.map((task) => (
                <TaskCard
                    key={task.id}
                    task={task}
                    setTasks={setTasks}
                />
            ))}
        </div>
    );
};
