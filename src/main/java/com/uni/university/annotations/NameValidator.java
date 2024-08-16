package com.uni.university.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

  private static final String PATTERN = "^[A-Za-z]{2,30}$";

  @Override
  public void initialize(ValidName constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext context) {
    if (s == null) {
      return false;
    }
    return s.matches(PATTERN);
  }
}

