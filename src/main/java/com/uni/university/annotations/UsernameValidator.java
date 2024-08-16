package com.uni.university.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

  private static final String PATTERN = "^[\\w\\-_.@]{2,30}$";

  @Override
  public void initialize(ValidUsername constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (s == null) {
      return false;
    }
    return s.matches(PATTERN);
  }
}

