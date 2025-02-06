package com.gsilva.springboot.app.gestortarea.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gsilva.springboot.app.gestortarea.services.TaskService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistDbValidation implements ConstraintValidator<IsExistDb, String>{
    
    @Autowired
    private TaskService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !service.existsByTitle(value);

    }
    

}
