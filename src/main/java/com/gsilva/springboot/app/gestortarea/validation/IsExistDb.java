package com.gsilva.springboot.app.gestortarea.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsExistDbValidation.class)
public @interface IsExistDb {

    String message() default "ya existe en la base de datos! Anotaciones personalizadas";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
