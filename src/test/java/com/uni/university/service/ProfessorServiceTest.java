package com.uni.university.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
import static utils.ProfessorUtils.getRandomProfessorDto;

import com.uni.university.domain.Gender;
import com.uni.university.domain.Professor;
import com.uni.university.dto.CreateUpdateProfessorDto;
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
    assertDoesNotThrow(() -> service.findAll());
    // TODO wrap every service test call with assertDoesNotThrow for appropriate scenarios

    verify(repository, times(1)).findAll();
  }

  @Test
  void testFindOrThrow() {
    var professor = getRandomProfessor();

    when(repository.findById(professor.getId())).thenReturn(Optional.of(professor));

    var result = assertDoesNotThrow(() -> service.findOrThrow(professor.getId()));

    verify(repository, times(1)).findById(professor.getId());
    assertEquals(professor, result); //TODO CHECK this one. assertEquals takes (expected, actual)
  }

  //TODO apply similar changes to every method in this class

  @Test
  void testFindOrThrow_throws() {

    Professor professor = getRandomProfessor();
    when(repository.findById(professor.getId())).thenReturn(Optional.empty());

    assertThrows(Exception.class, () -> service.findOrThrow(professor.getId()));

    verify(repository, times(1)).findById(professor.getId());
  }


  @Test
  void testSave() {
    Professor professor = getRandomProfessor();
    when(repository.save(any(Professor.class))).thenReturn(professor);

    Professor savedProfessor = service.save(professor);

    verify(repository, times(1)).save(professor);
    assertEquals(professor, savedProfessor);

  }


  @Test
  void testCreate_throws_by_not_null_ID() {
    CreateUpdateProfessorDto professorDto = getRandomProfessorDto();
    professorDto.setId(1L);

    assertThrows(Exception.class, () -> service.create(professorDto));

    verify(repository, never()).existsByUsername(anyString());
    verify(repository, never()).save(any(Professor.class));
  }

  @Test
  void testCreate_throws_by_username_exists() {
    CreateUpdateProfessorDto professorDto = getRandomProfessorDto();
    professorDto.setId(null);

    when(repository.existsByUsername(professorDto.getUsername())).thenReturn(true);

    assertThrows(Exception.class, () -> service.create(professorDto));

    verify(repository, times(1)).existsByUsername(professorDto.getUsername());
    verify(repository, never()).save(any(Professor.class));
  }

  @Test
  void testCreate_throws_name_more_than_thirty_characters() {
    CreateUpdateProfessorDto professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("f".repeat(31));

    assertThrows(Exception.class, () -> service.create(professorDto));
  }

  @Test
  void testCreate_throws_name_with_special_characters() {
    CreateUpdateProfessorDto professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("f@");

    assertThrows(Exception.class, () -> service.create(professorDto));
  }

  @Test
  void testCreate_throws_name_with_whitespaces() {
    CreateUpdateProfessorDto professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("f z");

    assertThrows(Exception.class, () -> service.create(professorDto));
  }

  @Test
  void testCreate_throws_name_with_empty_string() {
    CreateUpdateProfessorDto professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("");

    assertThrows(Exception.class, () -> service.create(professorDto));
  }

  @Test
  void testCreate_throws_name_with_numbers() {
    CreateUpdateProfessorDto professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("1");

    assertThrows(Exception.class, () -> service.create(professorDto));
  }

  @Test
  void testCreate_throws_name_less_than_two_characters() {
    CreateUpdateProfessorDto professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("f");

    assertThrows(Exception.class, () -> service.create(professorDto));

  }

  @Test
  void testCreate() {
    CreateUpdateProfessorDto professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    Professor professor = service.convertToProfessor(professorDto);

    when(repository.existsByUsername(professorDto.getUsername())).thenReturn(false);
    when(repository.save(any(Professor.class))).thenReturn(professor);

    Professor createdProfessor = service.create(professorDto);


    verify(repository, times(1)).existsByUsername(professorDto.getUsername());
    verify(repository, times(1)).save(any(Professor.class));
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
    CreateUpdateProfessorDto professorDto = getRandomProfessorDto();
    professorDto.setId(null);

    assertThrows(Exception.class, () -> service.update(professorDto));

    verify(repository, never()).findById(professorDto.getId());
    verify(repository, never()).save(any(Professor.class));
  }


  @Test
  void testUpdate_throws_while_user_input_adds_same_values_as_existing() {
    Professor professor = getRandomProfessor();
    professor.setId(1L);
    professor.setFirstName("fotis");
    professor.setLastName("zoumpos");
    professor.setEmail("f@z.gr");
    professor.setPhone("6980972601");
    professor.setGender(Gender.OTHER);
    professor.setBirthday(LocalDate.of(1993, 4, 4));

    CreateUpdateProfessorDto createUpdateProfessorDto = getRandomProfessorDto();
    createUpdateProfessorDto.setId(1L);
    createUpdateProfessorDto.setFirstName("fotis");
    createUpdateProfessorDto.setLastName("zoumpos");
    createUpdateProfessorDto.setEmail("f@z.gr");
    createUpdateProfessorDto.setPhone("6980972601");
    createUpdateProfessorDto.setGender(Gender.OTHER);
    createUpdateProfessorDto.setBirthday(LocalDate.of(1993, 4, 4));

    when(repository.findById(1L)).thenReturn(Optional.of(professor));

    assertThrows(Exception.class, () -> service.update(createUpdateProfessorDto));

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

    CreateUpdateProfessorDto updateProfessorDto = getRandomProfessorDto();
    updateProfessorDto.setId(1L);
    updateProfessorDto.setUsername("p");
    updateProfessorDto.setEmail("x@k.com");
    updateProfessorDto.setPhone("21034256978");
    updateProfessorDto.setGender(Gender.MALE);
    updateProfessorDto.setFirstName("xris");
    updateProfessorDto.setLastName("kelaidis");
    updateProfessorDto.setBirthday(LocalDate.of(2000, 1, 1));

    when(repository.findById(updateProfessorDto.getId())).thenReturn(Optional.of(professor));
    when(repository.save(professor)).thenReturn(professor);

    Professor updatedProfessor = service.update(updateProfessorDto);

    assertNotNull(updatedProfessor);
    assertEquals("p", updatedProfessor.getUsername());
    assertEquals("x@k.com", updatedProfessor.getEmail());
    assertEquals("21034256978", updatedProfessor.getPhone());
    assertEquals(Gender.MALE, updatedProfessor.getGender());
    assertEquals(LocalDate.of(2000, 1, 1), updatedProfessor.getBirthday());
    assertEquals("xris", updatedProfessor.getFirstName());
    assertEquals("kelaidis", updatedProfessor.getLastName());

    verify(repository, times(1)).save(professor);
  }


}
