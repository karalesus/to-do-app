import axios from "axios";
import {PRIORITY_MAP} from '../constants/constants.js';

const apiClient = axios.create({
    baseURL: "http://localhost:8080",
});

export const getAllTasks = async () => {
        const response = await apiClient.get('/api/tasks');
        return response.data.sort((a, b) => PRIORITY_MAP[b.priority] - PRIORITY_MAP[a.priority]);
}

export const addTask = async (task) => {
    const response = await apiClient.post("/api/tasks/add", task);
    return response.data;
};

export const handleDelete = async (id) => {
    const response = await apiClient.put(`/api/tasks/${id}`);
    return response.data;
};

export const handleComplete = async (id) => {
    const response = await apiClient.put(`/api/tasks/${id}/complete`);
    return response.data;
};

export const handleReturn = async (id) => {
    const response = await apiClient.put(`/api/tasks/${id}/return`);
    return response.data;
};


