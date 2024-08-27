package com.uni.university.validation;

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
    /*TODO there is a better way to implement it using com.google.i18n.phonenumbers.PhoneNumberUtil
    *  This is optionally and up to you to improve it*/
    if (s == null) {
      return true;
    }
    return s.matches(PATTERN);
  }
}
