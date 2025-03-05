package com.gsilva.springboot.app.gestortarea.services;

import java.util.List;

import com.gsilva.springboot.app.gestortarea.dto.CreateTaskDTO;
import com.gsilva.springboot.app.gestortarea.dto.TaskDTO;


public interface TaskService {

    List<TaskDTO> findAll();

    TaskDTO findById(Long id);

    List<TaskDTO> findByUserId(Long id);

    TaskDTO save (CreateTaskDTO task, Long userId);

    TaskDTO update(Long id, TaskDTO task, Long userId);

    void delete (Long id, Long userId);

    boolean existsByTitle(String title);    

    TaskDTO findByIdAndUserId (Long id, Long userId);  

}
