package com.uni.university.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER})
@Pattern(regexp = "^[A-Za-z]{2,30}$", message = "Name can contain from 2-30 letters and NO whitespaces.")
public @interface Name {
  String message() default "Invalid name";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
