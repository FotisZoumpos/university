package com.uni.university.annotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsernameValidatorTest {


  public UsernameValidator validator;

  @BeforeEach
  void setUp() {
    validator = new UsernameValidator();
  }

  @Test
  void testUsernameWithAtLeastTwoCharacters() {
    String username = "aa";
    assertTrue(validator.isValid(username, null));
  }

  @Test
  void testUsernameWithAtMostThirtyCharacters() {
    String username = "a".repeat(30);
    assertTrue(validator.isValid(username, null));
  }

  @Test
  void testUsernameWithLessThanTwoCharacters() {
    String username = "a";
    assertFalse(validator.isValid(username, null));
  }

  @Test
  void testUsernameWithMoreThanThirtyCharacters() {
    String username = "a".repeat(31);
    assertFalse(validator.isValid(username, null));
  }

  @Test
  void testUsernameWithSpecialCharacters() {
    String username = "-_.@";
    assertTrue(validator.isValid(username, null));
  }

  @Test
  void testUsernameWithWhitespacesBetweenCharacters() {
    String username = "aa aa";
    assertFalse(validator.isValid(username, null));
  }

  @Test
  void testUsernameWithNumbersAsCharacters() {
    String username = "111";
    assertTrue(validator.isValid(username, null));
  }

  @Test
  void testUsernameWithNull() {
    String username = null;
    assertFalse(validator.isValid(username, null));
  }

  @Test
  void testUsernameWithEmptyString() {
    String username = "";
    assertFalse(validator.isValid(username, null));
  }
}