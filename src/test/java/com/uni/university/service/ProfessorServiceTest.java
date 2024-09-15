package com.uni.university.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.ProfessorUtils.getRandomProfessor;
import static utils.ProfessorUtils.getRandomProfessorDto;

import com.uni.university.domain.Gender;
import com.uni.university.domain.Professor;
import com.uni.university.dto.CreateUpdateProfessorDto;
import com.uni.university.mappers.ProfessorMapper;
import com.uni.university.repository.ProfessorRepository;
import java.time.LocalDate;
import java.util.List;
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

  @Mock
  private ProfessorMapper professorMapper; // TODO this is wrong, we want the actual mapper, not a mock to perform as the mapper
  // TODO revert the change and find another way to provide this dependency

  @InjectMocks
  private ProfessorService service;

  // TODO now professor service doesn't get all the necessary dependencies, inject mocks -> gets only the repository and not the mapper, find a way to solve this

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

    var professor = getRandomProfessor();
    when(repository.findById(professor.getId())).thenReturn(Optional.empty());

    assertThrows(Exception.class, () -> service.findOrThrow(professor.getId()));

    verify(repository, times(1)).findById(professor.getId());
  }


  @Test
  void testSave() {
    var professor = getRandomProfessor();
    when(repository.save(any(Professor.class))).thenReturn(professor);

    var result = assertDoesNotThrow(() -> service.save(professor));

    verify(repository, times(1)).save(professor);
    assertEquals(professor, result);

  }


  @Test
  void testSave_throws_by_username_exists() {
    var professor = getRandomProfessor();
    professor.setId(null);

    when(repository.existsByUsername(professor.getUsername())).thenReturn(true);

    assertThrows(Exception.class, () -> service.save(professor));

    verify(repository, times(1)).existsByUsername(professor.getUsername());
    verify(repository, never()).save(professor);
  }


  @Test
  void testCreate_throws_by_not_null_ID() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(1L);

    assertThrows(Exception.class, () -> service.create(professorDto));

    verify(repository, never()).save(any(Professor.class));
  }

  @Test
  void testCreate_throws_by_username_exists() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setUsername("fotis");

    var professor = getRandomProfessor();
    professor.setId(null);
    professor.setUsername("fotis");

    when(professorMapper.professorDtoToProfessor(professorDto)).thenReturn(professor);
    when(repository.existsByUsername(professorDto.getUsername())).thenReturn(true);

    assertThrows(Exception.class, () -> service.create(professorDto));

    verify(professorMapper, times(1)).professorDtoToProfessor(professorDto);
    verify(repository, times(1)).existsByUsername(professorDto.getUsername());
    verify(repository, never()).save(any(Professor.class));
  }

  @Test
  void testCreate_throws_name_more_than_thirty_characters() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("f".repeat(31));

    assertThrows(Exception.class, () -> service.create(professorDto));
  }

  @Test
  void testCreate_throws_name_with_special_characters() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("f@");

    assertThrows(Exception.class, () -> service.create(professorDto));
  }

  @Test
  void testCreate_throws_name_with_whitespaces() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("f z");

    assertThrows(Exception.class, () -> service.create(professorDto));
  }

  @Test
  void testCreate_throws_name_with_empty_string() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("");

    assertThrows(Exception.class, () -> service.create(professorDto));
  }

  @Test
  void testCreate_throws_name_with_numbers() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("1");

    assertThrows(Exception.class, () -> service.create(professorDto));
  }

  @Test
  void testCreate_throws_name_less_than_two_characters() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName("f");

    assertThrows(Exception.class, () -> service.create(professorDto));

  }

  @Test
  void testCreate() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setUsername("fotis");

    var professor = getRandomProfessor();
    professor.setId(null);
    professor.setUsername("fotis");

    when(professorMapper.professorDtoToProfessor(professorDto)).thenReturn(professor);

    when(repository.existsByUsername("fotis")).thenReturn(false);
    when(repository.save(any(Professor.class))).thenReturn(professor);

    Professor createdProfessor = assertDoesNotThrow(() -> service.create(professorDto));


    verify(repository, times(1)).existsByUsername("fotis");
    verify(repository, times(1)).save(any(Professor.class));
    verify(professorMapper, times(1)).professorDtoToProfessor(professorDto);
    assertEquals(createdProfessor, professor);
  }

  @Test
  void testDeleteById() {
    var professor = getRandomProfessor();
    Long professorId = professor.getId();

    assertDoesNotThrow(() -> service.deleteById(professorId));
    verify(repository, times(1)).deleteById(professorId);

  }

  @Test
  void testDeleteAll() {
    List<Long> professorIds = List.of(1L, 2L, 3L);
    assertDoesNotThrow(() -> service.deleteAll(professorIds));
    verify(repository, times(1)).deleteAllByIdInBatch(professorIds);

  }

  @Test
  void update_throws_by_null_ID() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(null);

    assertThrows(Exception.class, () -> service.update(professorDto));

    verify(repository, times(1)).findById(professorDto.getId());
    verify(repository, never()).save(any(Professor.class));
  }


  @Test
  void testUpdate_throws_while_user_input_adds_same_values_as_existing() {
    var professor = getRandomProfessor();
    professor.setId(1L);
    professor.setFirstName("fotis");
    professor.setLastName("zoumpos");
    professor.setUsername("paralias");
    professor.setEmail("f@z.gr");
    professor.setPhone("6980972601");
    professor.setGender(Gender.OTHER);
    professor.setBirthday(LocalDate.of(1993, 4, 4));

    var createUpdateProfessorDto = getRandomProfessorDto();
    createUpdateProfessorDto.setId(1L);
    createUpdateProfessorDto.setFirstName("fotis");
    createUpdateProfessorDto.setLastName("zoumpos");
    createUpdateProfessorDto.setUsername("paralias");
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
    var professor = getRandomProfessor();
    professor.setId(1L);
    professor.setUsername("q");
    professor.setEmail("f@z.com");
    professor.setPhone("6987451230");
    professor.setGender(Gender.OTHER);
    professor.setFirstName("fotis");
    professor.setLastName("zoumpos");
    professor.setBirthday(LocalDate.of(1993, 4, 4));

    var updateProfessorDto = getRandomProfessorDto();
    updateProfessorDto.setId(1L);
    updateProfessorDto.setUsername("p");
    updateProfessorDto.setEmail("x@k.com");
    updateProfessorDto.setPhone("21034256978");
    updateProfessorDto.setGender(Gender.MALE);
    updateProfessorDto.setFirstName("xris");
    updateProfessorDto.setLastName("kelaidis");
    updateProfessorDto.setBirthday(LocalDate.of(2000, 1, 1));

    when(repository.findById(1L)).thenReturn(Optional.of(professor));
    when(repository.save(professor)).thenReturn(professor);

    var updatedProfessor = assertDoesNotThrow(() -> service.update(updateProfessorDto));

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

  @Test
  void professorDto_to_professor() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(1L);
    professorDto.setFirstName("fotis");
    professorDto.setLastName("zoumpos");
    professorDto.setUsername("z");
    professorDto.setEmail("f@z.gr");
    professorDto.setPhone("6980972601");
    professorDto.setBirthday(LocalDate.of(1993, 4, 4));
    professorDto.setGender(Gender.OTHER);

    var professor = getRandomProfessor();
    professor.setId(professorDto.getId());
    professor.setFirstName(professorDto.getFirstName());
    professor.setLastName(professorDto.getLastName());
    professor.setUsername(professorDto.getUsername());
    professor.setEmail(professorDto.getEmail());
    professor.setPhone(professorDto.getPhone());
    professor.setBirthday(professorDto.getBirthday());
    professor.setGender(professorDto.getGender());

    when(professorMapper.professorDtoToProfessor(any(CreateUpdateProfessorDto.class))).thenReturn(professor);
    Professor result = professorMapper.professorDtoToProfessor(professorDto);

    assertNotNull(result);
    assertEquals(professorDto.getId(), professor.getId());
    assertEquals(professorDto.getFirstName(), professor.getFirstName());
    assertEquals(professorDto.getLastName(), professor.getLastName());
    assertEquals(professorDto.getUsername(), professor.getUsername());
    assertEquals(professorDto.getEmail(), professor.getEmail());
    assertEquals(professorDto.getPhone(), professor.getPhone());
    assertEquals(professorDto.getBirthday(), professor.getBirthday());
    assertEquals(professorDto.getGender(), professor.getGender());
  }

  @Test
  void professorDto_to_professor_with_null_values() {
    var professorDto = getRandomProfessorDto();
    professorDto.setId(null);
    professorDto.setFirstName(null);
    professorDto.setLastName(null);
    professorDto.setUsername(null);
    professorDto.setEmail(null);
    professorDto.setPhone(null);
    professorDto.setBirthday(null);
    professorDto.setGender(null);

    var professor = getRandomProfessor();
    professor.setId(professorDto.getId());
    professor.setFirstName(professorDto.getFirstName());
    professor.setLastName(professorDto.getLastName());
    professor.setUsername(professorDto.getUsername());
    professor.setEmail(professorDto.getEmail());
    professor.setPhone(professorDto.getPhone());
    professor.setBirthday(professorDto.getBirthday());
    professor.setGender(professorDto.getGender());

    when(professorMapper.professorDtoToProfessor(any(CreateUpdateProfessorDto.class))).thenReturn(null);
    Professor result = professorMapper.professorDtoToProfessor(professorDto);

    assertNull(result);
    assertNull(professor.getId());
    assertNull(professor.getFirstName());
    assertNull(professor.getLastName());
    assertNull(professor.getUsername());
    assertNull(professor.getEmail());
    assertNull(professor.getPhone());
    assertNull(professor.getBirthday());
    assertNull(professor.getGender());


  }

}
