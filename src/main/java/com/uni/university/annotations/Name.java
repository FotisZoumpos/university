package com.uni.university.annotations;


import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^[A-Za-z]{2,30}$", message = "Name can contain from 2-30 letters and NO whitespaces.")
public @interface Name {
  String message() default "Invalid name";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
