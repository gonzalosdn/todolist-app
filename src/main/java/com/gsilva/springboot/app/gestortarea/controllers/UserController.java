package com.gsilva.springboot.app.gestortarea.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsilva.springboot.app.gestortarea.entities.AppUser;
import com.gsilva.springboot.app.gestortarea.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;  

    @GetMapping("/users")
    public List<AppUser> list(){
        return userService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody AppUser user, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        user.setAdmin(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
  

}
