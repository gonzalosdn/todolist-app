package com.gsilva.springboot.app.gestortarea.services;

import java.util.List;
import java.util.Optional;

import com.gsilva.springboot.app.gestortarea.entities.Task;


public interface TaskService {

    List<Task> findAll();

    Task findById(Long id);

    List<Task> findByUserId(Long id);

    Task save (Task task);

    Task update(Long id, Task task);

    boolean delete (Long id);

    boolean existsByTitle(String title);

    Task findByTitle(String title);

    Task findByIdAndUserId (Long id, Long userId);
  

}
