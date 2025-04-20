package com.gsilva.springboot.app.gestortarea.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsilva.springboot.app.gestortarea.dto.CreateTaskDTO;
import com.gsilva.springboot.app.gestortarea.dto.TaskDTO;
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
    public List<TaskDTO> findAll() {

        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add); // lo hago de esta manera porque el findAll de CrudRepository
                                                      // devuelve un iterable y si hago List<Task> tasks =
                                                      // taskRepository.findAll() me va a pedir hacer un cast de List si
                                                      // o si.
        List<TaskDTO> tasksDTO = new ArrayList<>();
        tasks.forEach(task -> tasksDTO.add(toDTO(task)));
        return tasksDTO;

    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO findById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return toDTO(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findByUserId(Long userId) {
        List<Task> taskList = taskRepository.findByUserId(userId);
        List<TaskDTO> taskListDTO = new ArrayList<>();
        taskList.forEach(task -> taskListDTO.add(toDTO(task)));
        return taskListDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO findByIdAndUserId(Long id, Long userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return toDTO(task);
    }

    @Override
    @Transactional
    public TaskDTO save(CreateTaskDTO taskDTO,Long userId) {
        Task task = createTaskToEntity(taskDTO);
        task.setUserId(userId);
        taskRepository.save(task);
        return toDTO(task);
    }

    @Override
    @Transactional
    public TaskDTO update(Long id, TaskDTO taskDTO, Long userId) {

        Task dbTask = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (taskDTO.getTitle() != null) {
            dbTask.setTitle(taskDTO.getTitle());
        }

        if (taskDTO.getDescription() != null) {
            dbTask.setDescription(taskDTO.getDescription());
        }

        if (taskDTO.getCreatedAt() != null) {
            dbTask.setCreatedAt(taskDTO.getCreatedAt());
        }

        if (taskDTO.isStatus() != null) {
            dbTask.setStatus(taskDTO.isStatus());
        }

        taskRepository.save(dbTask);
        return toDTO(dbTask);
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

    private TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getId(),task.getTitle(), task.getDescription(), task.getCreatedAt(), task.isStatus());
    }   
    
    private Task createTaskToEntity(CreateTaskDTO taskDTO){
        return new Task(taskDTO.getTitle(), taskDTO.getDescription());

    }

}
