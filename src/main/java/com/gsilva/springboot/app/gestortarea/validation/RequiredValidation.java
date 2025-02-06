package com.gsilva.springboot.app.gestortarea.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredValidation implements ConstraintValidator<IsRequired, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //return (value != null && !value.isBlank());

        return StringUtils.hasText(value); // esto es lo mismo que la linea anterior de retorno            
    }

}
