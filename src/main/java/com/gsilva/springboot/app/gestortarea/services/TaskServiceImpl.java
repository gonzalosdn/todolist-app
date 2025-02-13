package com.gsilva.springboot.app.gestortarea.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsilva.springboot.app.gestortarea.entities.Task;
import com.gsilva.springboot.app.gestortarea.exceptions.TaskNotFoundException;
import com.gsilva.springboot.app.gestortarea.repositories.TaskRepository;


@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

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
    public List<Task> findByUserId(Long userId){
        List<Task> taskList = taskRepository.findByUserId(userId);
        return taskList;
    }

    @Override
    @Transactional(readOnly = true)
    public Task findByIdAndUserId (Long id, Long userId){
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        if (!task.getUserId().equals(userId)){
            throw new AccessDeniedException("You don't have permission to access this task");
        }
        return task;
    }

    @Override
    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task update(Long id, Task task) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        LocalDateTime utcDueDate = task.getDueDate().atOffset(ZoneOffset.UTC).toLocalDateTime();    
        if (optionalTask.isPresent()){
            Task tsk = optionalTask.orElseThrow();
            tsk.setTitle(task.getTitle());
            tsk.setDescription(task.getDescription());
            tsk.setDueDate(utcDueDate);
            System.out.println(utcDueDate);
            tsk.setStatus(task.isStatus());

            return Optional.of(taskRepository.save(tsk));
        }     
        
        return optionalTask;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
                
        taskRepository.delete(task);
        
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByTitle(String title){
        return taskRepository.existsByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public Task findByTitle(String title){
        return taskRepository.findByTitle(title);
    };

    

}
