package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.dto.FinishedTaskOutputDTO;
import org.example.dto.TaskOutputDTO;
import org.example.models.Status;
import org.example.models.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TaskRepositoryImpl extends BaseRepository<Task, UUID> implements TaskRepository{
    @PersistenceContext
    EntityManager entityManager;

    public TaskRepositoryImpl() {
        super(Task.class);
    }

    @Override
    public List<Task> findTasksByStatus(Status status) {
        var query = entityManager.createQuery("SELECT t FROM Task t WHERE t.status = (:status)", Task.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
}
