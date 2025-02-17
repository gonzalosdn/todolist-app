package com.gsilva.springboot.app.gestortarea.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsilva.springboot.app.gestortarea.entities.Task;
import com.gsilva.springboot.app.gestortarea.exceptions.TaskNotFoundException;
import com.gsilva.springboot.app.gestortarea.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Este metodo es solo para pruebas, no es recomendable tenerlo ya que trae a
    // todas las tareas sin filtrar usuarios.
    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {

        return (List<Task>) taskRepository.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findByUserId(Long userId) {
        List<Task> taskList = taskRepository.findByUserId(userId);
        return taskList;
    }

    @Override
    @Transactional(readOnly = true)
    public Task findByIdAndUserId(Long id, Long userId) {
        return taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));

    }

    @Override
    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task update(Long id, Task task, Long userId) {

        Task dbTask = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (task.getTitle() != null) {
            dbTask.setTitle(task.getTitle());
        }

        if (task.getDescription() != null) {
            dbTask.setDescription(task.getDescription());
        }

        if (task.getDueDate() != null) {
            dbTask.setDueDate(task.getDueDate());
        }

        if (task.isStatus() != null) {
            dbTask.setStatus(task.isStatus());
        }

        return taskRepository.save(dbTask);
    }

    @Override
    @Transactional
    public void delete(Long id, Long userId) {

        Task task = taskRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByTitle(String title) {
        return taskRepository.existsByTitle(title);
    }

}
