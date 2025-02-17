package com.gsilva.springboot.app.gestortarea.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsilva.springboot.app.gestortarea.entities.Task;
import com.gsilva.springboot.app.gestortarea.services.CustomUserDetails;
import com.gsilva.springboot.app.gestortarea.services.TaskService;

import jakarta.validation.Valid;

@RestController
// @CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // Listar tareas segun usuario
    @GetMapping
    public ResponseEntity<List<Task>> list(Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUserId(userId));
    }

    // Obtener tarea segun id y id de usuario (se compara que el usuario sea el
    // mismo de la tarea que el logeado actualmente)
    @GetMapping("/{id}")
    public ResponseEntity<Task> findOne(Authentication authentication, @PathVariable Long id) {
        Long userId = getCurrentUserId(authentication);        
        return ResponseEntity.ok(service.findByIdAndUserId(id, userId));
    }

    // Modificar tarea segun id y id de usuario (se compara que el usuario sea el
    // mismo de la tarea que el logeado actualmente)
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@RequestBody Task task, Authentication authentication,
            @PathVariable Long id) {
        Long userId = getCurrentUserId(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, task, userId));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Task task, BindingResult result,
            Authentication authentication) {

        if (result.hasFieldErrors()) {
            return validation(result);
        }

        task.setUserId(getCurrentUserId(authentication));

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Authentication authentication, @PathVariable Long id) {
        Long userId = getCurrentUserId(authentication);
        service.delete(id, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private Long getCurrentUserId(Authentication authentication) {
        return ((CustomUserDetails) authentication.getPrincipal()).getId();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors); // badRequest o status(HttpStatus.BAD_REQUEST) o status(400)
    }

}