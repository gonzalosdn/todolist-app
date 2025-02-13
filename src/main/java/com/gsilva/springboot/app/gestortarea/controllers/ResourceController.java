package com.gsilva.springboot.app.gestortarea.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsilva.springboot.app.gestortarea.exceptions.UserNotFoundException;
import com.gsilva.springboot.app.gestortarea.services.CustomUserDetails;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping("/user")
    public ResponseEntity<?> user(Authentication authentication) {
        return ResponseEntity.ok().body("Bienvenido " + authentication.getName() + ". Tus permisos son: "
                + authentication.getAuthorities() + " y tu Id de usuario es: " + getCurrentUserId(authentication));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // asegurarse de que en securityConfig este la anotacion
                                                // @EnableMethodSecurity para que funcionen estas anotaciones de
                                                // autorizacion.
    @GetMapping("/admin")
    public ResponseEntity<?> admin(Authentication authentication) {
        return ResponseEntity.ok().body("Bienvenido Administrador: " + authentication.getName() + ". Tus permisos son: "
                + authentication.getAuthorities() + " y tu Id de usuario es: " + getCurrentUserId(authentication));
    }

    private Long getCurrentUserId(Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return userId;

    }

}
