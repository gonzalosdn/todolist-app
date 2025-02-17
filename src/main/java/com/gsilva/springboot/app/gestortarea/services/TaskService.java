package com.gsilva.springboot.app.gestortarea.services;

import java.util.List;

import com.gsilva.springboot.app.gestortarea.entities.Task;


public interface TaskService {

    List<Task> findAll();

    Task findById(Long id);

    List<Task> findByUserId(Long id);

    Task save (Task task);

    Task update(Long id, Task task, Long userId);

    void delete (Long id, Long userId);

    boolean existsByTitle(String title);    

    Task findByIdAndUserId (Long id, Long userId);  

}
