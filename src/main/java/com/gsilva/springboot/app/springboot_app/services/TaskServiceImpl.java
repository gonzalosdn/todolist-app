package com.gsilva.springboot.app.springboot_app.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsilva.springboot.app.springboot_app.entities.Task;
import com.gsilva.springboot.app.springboot_app.repositories.TaskRepository;


@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository repositoryTask;

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {

        return (List<Task>) repositoryTask.findAll();
        
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Task> findById(Long id) {
        return repositoryTask.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Task>> findByUserId(Long userId){
        Optional<List<Task>> optionalTasks = repositoryTask.findByUserId(userId);
        return optionalTasks;
    }

    @Override
    @Transactional
    public Task save(Task task) {
        return repositoryTask.save(task);
    }

    @Override
    @Transactional
    public Optional<Task> update(Long id, Task task) {

        Optional<Task> optionalTask = repositoryTask.findById(id);
        LocalDateTime utcDueDate = task.getDueDate().atOffset(ZoneOffset.UTC).toLocalDateTime();    
        if (optionalTask.isPresent()){
            Task tsk = optionalTask.orElseThrow();
            tsk.setTitle(task.getTitle());
            tsk.setDescription(task.getDescription());
            tsk.setDueDate(utcDueDate);
            tsk.setStatus(task.isStatus());

            return Optional.of(repositoryTask.save(tsk));
        }     
        
        return optionalTask;
    }

    @Override
    @Transactional
    public Optional<Task> delete(Long id) {

        Optional<Task> optionalTask = repositoryTask.findById(id);

        if (optionalTask.isPresent()){

            repositoryTask.delete(optionalTask.get());

        }

        //optionalTask.ifPresent(tsk -> repositoryTask.delete(tsk)); otra opcion con programacion funcional
        
        return optionalTask;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByTitle(String title){
        return repositoryTask.existsByTitle(title);
    }

    

}
