package com.uni.university.annotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameValidatorTest {

  public NameValidator validator;

  @BeforeEach
  void setUp() {
    validator = new NameValidator();
  }

  @Test
  void testValidNameWithEmptyString() {
    String name = "";
    assertFalse(validator.isValid(name, null));
  }

  @Test
  void testValidNameWithNull() {
    String name = null;
    assertFalse(validator.isValid(name, null));
  }

  @Test
  void testValidNameWithAtLeastTwoCharacters() {

    String name = "aa";
    assertTrue(validator.isValid(name, null));
  }

  @Test
  void testValidNameWithAtMostThirtyCharacters() {
    String name = "a".repeat(30);
    assertTrue(validator.isValid(name, null));
  }

  @Test
  void testValidNameWithLessThanTwoCharacters() {
    String name = "a";
    assertFalse(validator.isValid(name, null));
  }

  @Test
  void testValidNameWithMoreThanThirtyCharacters() {
    String name = "a".repeat(31);
    assertFalse(validator.isValid(name, null));
  }

  @Test
  void testValidNameWithSpecialCharacters() {
    String name = "aaa@gg";
    assertFalse(validator.isValid(name, null));
  }

  @Test
  void testValidNameWithSpacesBetweenCharacters() {
    String name = "aaa aaa";
    assertFalse(validator.isValid(name, null));
  }

  @Test
  void testValidNameWithNumbersAsCharacters() {
    String name = "aaa111111";
    assertFalse(validator.isValid(name, null));
  }


}