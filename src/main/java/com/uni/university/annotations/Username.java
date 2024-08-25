package com.uni.university.annotations;

import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^[\\w\\-_.@]{2,30}$", message = "Username can be from 2-30 including special characters and NO whitespaces.")
public @interface Username {
  String message() default "Invalid username";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
