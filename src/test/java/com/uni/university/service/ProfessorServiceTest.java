package com.uni.university.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.ProfessorUtils.getRandomProfessor;

import com.uni.university.domain.Professor;
import com.uni.university.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {

  @Mock
  private ProfessorRepository repository;

  @InjectMocks
  private ProfessorService service;

  @Test
  void testSave() {
    // Arrange
    Professor professor = getRandomProfessor();
    when(repository.save(any(Professor.class))).thenReturn(professor);

    // Act
    Professor savedProfessor = service.save(professor);

    //Assert
    verify(repository, times(1)).save(professor);
  }

  /*How to test:
  * Naming: testMethodName_whatYouTest_anyAdditionalInfo
  * px: testCreate_throwsByUsernameAlreadyExists
  * if you want to test the whole method testCreate makes sense
  * for every conditional branch you have to create the corresponding test
  * most acts produce a result, you might want to assert the result
  * common assertions:
  *  assertEquals -> checks equality of fields
  *  assertNull -> checks if a field is null
  *  verify -> verifies that a method was called with the correct argument
  * */

}
