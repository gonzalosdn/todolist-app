package com.gsilva.springboot.app.gestortarea.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RequiredValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IsRequired {

    String message() default "es requerido. No puede ser null ni ser un espacio vacio! Anotaciones personalizadas";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
