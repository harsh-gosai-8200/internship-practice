package com.springboot.spring_mvc_validators.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstrainValdator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    //define default course code
    public String value() default "JAY";

    //define default error message
    public String message() default "must start with JAY";

    //define default groups
    public Class<?>[] groups() default {};

    //define default payload
    public Class<? extends Payload>[] payload() default {};

}
