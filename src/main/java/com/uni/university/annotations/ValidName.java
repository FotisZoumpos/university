package com.uni.university.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^[A-Za-z]{2,30}$", message = "Name can contain from 2-30 characters and NO whitespaces.")
public @interface ValidName {
  String message() default "Invalid name";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
