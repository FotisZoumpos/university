package com.uni.university.annotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PhoneValidatorTest {

  public PhoneValidator validator;

  @BeforeEach
  void setUp() {
    validator = new PhoneValidator();
  }

  @Test
  void testPhoneItContainsNumbers() {

    String phone = "6534567890";
    assertTrue(validator.isValid(phone, null));
  }

  @Test
  void testPhoneWithMoreThanTenDigits() {
    String phone = "1".repeat(11);
    assertFalse(validator.isValid(phone, null));
  }

  @Test
  void testPhoneWithLessThanTenDigits() {
    String phone = "1".repeat(9);
    assertFalse(validator.isValid(phone, null));
  }

  @Test
  void testPhoneWithExactlyTenDigits() {
    String phone = "1".repeat(10);
    assertTrue(validator.isValid(phone, null));
  }

  @Test
  void testPhoneIfItContainsCharacters() {
    String phone = "a";
    assertFalse(validator.isValid(phone, null));
  }

  @Test
  void testPhoneIfContainsPlusSymbol() {
    String phone = "+6534567890";
    assertTrue(validator.isValid(phone, null));
  }

  @Test
  void testPhoneIfNotContainsOtherSymbols() {
    String phone = "_-.";
    assertFalse(validator.isValid(phone, null));
  }

}