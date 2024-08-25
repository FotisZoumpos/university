package com.uni.university.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

  private static final String PATTERN = "^\\+?[0-9]{10,15}$";

  @Override
  public void initialize(Phone constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (s == null) {
      return true;
    }
    return s.matches(PATTERN);
  }
}
