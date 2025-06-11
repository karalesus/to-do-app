import ActiveTasks from "./ActiveTasks";
import FinishedTasks from "./FinishedTasks";
import styles from "../styles/AllTasks.module.css"

export default function ViewTasks({ tasks, setTasks }) {
    return (
        <div className={styles.allTasks}>
            <ActiveTasks tasks={tasks} setTasks={setTasks} />
            <FinishedTasks tasks={tasks} setTasks={setTasks}  />
        </div>
    );
}
