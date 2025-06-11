import {PRIORITY_MAP} from "../constants/constants.js";

export function formatDate(dateString) {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
    const hour = date.getHours();
    const minutes = String(date.getMinutes()).padEnd(2, '0');
    return `${day}.${month}.${year} ${hour}:${minutes}`;
}

export function sortTasksByPriority(tasks) {
    return tasks.slice().sort((a, b) => PRIORITY_MAP[b.priority] - PRIORITY_MAP[a.priority]);
}