package com.gsilva.springboot.app.springboot_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsilva.springboot.app.springboot_app.entities.AppUser;
import com.gsilva.springboot.app.springboot_app.services.UserService;

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
    public ResponseEntity<AppUser> createUser(@Valid @RequestBody AppUser user){
        user.setAdmin(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

  

}
