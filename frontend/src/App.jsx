import { useEffect, useState} from 'react'
import './App.module.css'
import ViewTasks from "./components/ViewTasks.jsx";
import {getAllTasks} from "./api/ApiClient.js";
import TaskForm from "./components/TaskForm.jsx";
import ErrorMessage from "./components/ErrorMessage.jsx";
import styles from "./App.module.css";
import {sortTasksByPriority} from "./utils/utils.js"


function App() {
    const [tasks, setTasks] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);


    useEffect(() => {
            const loadTasks = async () => {
                try {
                    setLoading(true);
                    const data = await getAllTasks();
                    setTasks(data);
                    setLoading(false);
                    setError(null);
                } catch (error) {
                    setLoading(true);
                    if (error.response?.data) {
                        setError(error.response.data);
                        setTimeout(loadTasks, 3000);
                    } else {
                        setLoading(true);
                        setError("Ошибка соединения. Невозможно загрузить задачи");
                        setTimeout(loadTasks, 3000);
                    }
                }
            };
            loadTasks();
        }, []);


    const updateTasks = (newTask) => {
        setTasks((prev) => {
                const updatedTasks = [...prev, newTask];
                return sortTasksByPriority(updatedTasks);
            }
        );
    };

    return (
        <div className={styles.app}>
            <header>
                <h1 className={styles.header}>
                    To-Do List
                </h1>
            </header>
            {error && <ErrorMessage message={error}/>}
            {loading && <p className={styles.loading}>Загрузка задач...</p>}
            <div className={styles.container}>
                <TaskForm onTaskAdded={updateTasks}/>
                <ViewTasks tasks={tasks} setTasks={setTasks}/>
            </div>
        </div>
    );

}

export default App
