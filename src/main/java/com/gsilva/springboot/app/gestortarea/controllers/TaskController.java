package com.gsilva.springboot.app.gestortarea.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsilva.springboot.app.gestortarea.entities.Task;
import com.gsilva.springboot.app.gestortarea.exceptions.UserNotFoundException;
import com.gsilva.springboot.app.gestortarea.services.CustomUserDetails;
import com.gsilva.springboot.app.gestortarea.services.TaskService;
import com.gsilva.springboot.app.gestortarea.validation.IsExistDbValidation;

import jakarta.validation.Valid;

@RestController
// @CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public List<Task> list(Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        return service.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findOne(Authentication authentication, @PathVariable Long id) {

        Long userId = getCurrentUserId(authentication);
        Task task = service.findByIdAndUserId(id, userId);

        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Task task, Authentication authentication,
            @PathVariable Long id) {

        Optional<Task> optionalTask = service.update(id, task);

        if (optionalTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(optionalTask.get());

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Task task, BindingResult result,
            Authentication authentication) {

        task.setUserId(getCurrentUserId(authentication));
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(Authentication authentication, @PathVariable Long id) {
        boolean optionalTask = service.delete(id);
        Long userId = getCurrentUserId(authentication);

        if (!optionalTask.get().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(optionalTask.get());
    }

    private Long getCurrentUserId(Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return userId;

    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors); // badRequest o status(HttpStatus.BAD_REQUEST) o status(400)
    }

}