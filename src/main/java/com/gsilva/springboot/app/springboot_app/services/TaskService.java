package com.gsilva.springboot.app.springboot_app.services;

import java.util.List;
import java.util.Optional;

import com.gsilva.springboot.app.springboot_app.entities.Task;


public interface TaskService {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Optional<List<Task>> findByUserId(Long id);

    Task save (Task task);

    Optional<Task> update(Long id, Task task);

    Optional<Task> delete (Long id);

    boolean existsByTitle(String title);

  

}
