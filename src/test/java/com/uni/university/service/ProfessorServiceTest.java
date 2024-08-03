package com.uni.university.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.ProfessorUtils.getRandomProfessor;

import com.uni.university.domain.Gender;
import com.uni.university.domain.Professor;
import com.uni.university.repository.ProfessorRepository;
import java.time.LocalDate;
import java.util.Optional;
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
  void testFindAll() {
    service.findAll();
    verify(repository, times(1)).findAll();

  }

  @Test
  void testFindOrThrow() {
    Professor professor = getRandomProfessor();
    when(repository.findById(professor.getId())).thenReturn(Optional.of(professor));

    Professor foundProfessor = service.findOrThrow(professor.getId());

    verify(repository, times(1)).findById(professor.getId());
    assertEquals(foundProfessor, professor);

  }

  @Test
  void testFindOrThrow_throws() {

    Professor professor = getRandomProfessor();
    when(repository.findById(professor.getId())).thenReturn(Optional.empty());

    assertThrows(Exception.class, () -> service.findOrThrow(professor.getId()));

    verify(repository, times(1)).findById(professor.getId());
  }


  @Test
  void testSave() {
    // Arrange
    Professor professor = getRandomProfessor();
    when(repository.save(any(Professor.class))).thenReturn(professor);

    // Act
    Professor savedProfessor = service.save(professor);

    //Assert
    verify(repository, times(1)).save(professor);
    assertEquals(professor, savedProfessor);

  }


  @Test
  void testCreate_throws_by_not_null_ID() {
    Professor professor = getRandomProfessor();


    assertThrows(Exception.class, () -> service.create(professor));

    verify(repository, never()).existsByUsername(anyString());
    verify(repository, never()).save(any(Professor.class));
  }

  @Test
  void testCreate_throws_by_username_exists() {
    Professor professor = getRandomProfessor();
    professor.setId(null);

    when(repository.existsByUsername(professor.getUsername())).thenReturn(true);

    assertThrows(Exception.class, () -> service.create(professor));

    verify(repository, times(1)).existsByUsername(professor.getUsername());
    verify(repository, never()).save(any(Professor.class));
  }


  @Test
  void testCreate() {
    Professor professor = getRandomProfessor();
    professor.setId(null);

    when(repository.existsByUsername(professor.getUsername())).thenReturn(false);
    when(repository.save(any(Professor.class))).thenReturn(professor);

    Professor createdProfessor = service.create(professor);

    verify(repository, times(1)).existsByUsername(professor.getUsername());
    verify(repository, times(1)).save(professor);
    assertEquals(createdProfessor, professor);
  }

  @Test
  void testDeleteById() {
    Professor professor = getRandomProfessor();
    Long professorId = professor.getId();

    repository.deleteById(professorId);
    verify(repository, times(1)).deleteById(professorId);

  }

  @Test
  void testDeleteAll() {
    repository.deleteAll();
    verify(repository, times(1)).deleteAll();

  }

  @Test
  void update_throws_by_null_ID() {
    Professor professor = getRandomProfessor();
    professor.setId(null);

    assertThrows(Exception.class, () -> service.update(professor));
    verify(repository, never()).findById(professor.getId());
    verify(repository, never()).save(any(Professor.class));
  }

  @Test
  void update() {
    Professor professor = getRandomProfessor();
    professor.setId(1L);
    professor.setUsername("q");
    professor.setEmail("f@z.com");
    professor.setPhone("6987451230");
    professor.setGender(Gender.OTHER);
    professor.setFirstName("fotis");
    professor.setLastName("zoumpos");
    professor.setBirthday(LocalDate.of(1993, 4, 4));

    Professor updateProfessor = new Professor();
    updateProfessor.setId(1L);
    professor.setUsername("q");
    updateProfessor.setEmail("x@k.com");
    updateProfessor.setPhone("21034256978");
    updateProfessor.setGender(Gender.MALE);
    updateProfessor.setFirstName("xris");
    updateProfessor.setLastName("kelaidis");
    updateProfessor.setBirthday(LocalDate.of(2000, 1, 1));

    when(repository.findById(updateProfessor.getId())).thenReturn(Optional.of(professor));
    when(repository.save(professor)).thenReturn(professor);

    Professor updatedProfessor = service.update(updateProfessor);

    assertNotNull(updatedProfessor);
    assertEquals("x@k.com", updateProfessor.getEmail());
    assertEquals("21034256978", updateProfessor.getPhone());
    assertEquals(Gender.MALE, updateProfessor.getGender());
    assertEquals(LocalDate.of(2000, 1, 1), updateProfessor.getBirthday());
    assertEquals("xris", updateProfessor.getFirstName());
    assertEquals("kelaidis", updateProfessor.getLastName());

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
