package com.uni.university.service;

import com.uni.university.constraints.CreateGroup;
import com.uni.university.domain.Professor;
import com.uni.university.dto.CreateUpdateProfessorDto;
import com.uni.university.repository.ProfessorRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class ProfessorService {

  private final ProfessorRepository repository;

  //TODO All Exceptions should be replaced with customised university exceptions

  public List<Professor> findAll() {
    return repository.findAll();
  }

  @SneakyThrows
  public Professor findOrThrow(Long id) {
    return repository.findById(id).orElseThrow(Exception::new);
  }

  public Professor save(Professor professor) {
    return repository.save(professor);
  }


  @SneakyThrows
  @Validated(CreateGroup.class)
  public Professor create(CreateUpdateProfessorDto professorDto) {
    //TODO replace with proper DTO

    if (professorDto.getId() != null) {
      throw new Exception();
    }
    if (repository.existsByUsername(professorDto.getUsername())) {
      throw new Exception();
    }

    Professor professor = convertToProfessor(professorDto);

    return repository.save(professor);
  }

  @SneakyThrows
  public Professor update(CreateUpdateProfessorDto professorDto) {
    if (professorDto.getId() == null) {
      throw new Exception();
    }
    Professor existingProfessor = findOrThrow(professorDto.getId());

    if (professorDto.getFirstName() != null) {
      existingProfessor.setFirstName(professorDto.getFirstName());
    }
    if (professorDto.getLastName() != null) {
      existingProfessor.setLastName(professorDto.getLastName());
    }
    if (professorDto.getEmail() != null) {
      existingProfessor.setEmail(professorDto.getEmail());
    }
    if (professorDto.getPhone() != null) {
      existingProfessor.setPhone(professorDto.getPhone());
    }
    if (professorDto.getGender() != null) {
      existingProfessor.setGender(professorDto.getGender());
    }
    if (professorDto.getBirthday() != null) {
      existingProfessor.setBirthday(professorDto.getBirthday());
    }

    return save(existingProfessor);
  }



  /* public Professor update(Professor professor) {
   * The logic for the update is this:
   * If no id is provided -> means it is not an update
   * If id is provided fetch the professor from database and update his!!! fields
   * return the newly saved professor
   * NOTE: Some fields should not be able to get updated
   * which??
   * how we should proceed with this?
   * } */

  public void deleteById(Long id) {
    /*Let's leave it like this for now and deal with the courses later */
    repository.deleteById(id);
  }

  public void deleteAll(List<Long> professorIds) {
    /*Let's leave it like this for now and deal with the courses later */
    repository.deleteAllByIdInBatch(professorIds);
  }

  public List<CreateUpdateProfessorDto> getAllInfo() {
    return repository.findAll().stream().map(this::convertToProfessorDto).collect(Collectors.toList());
  }

  @SneakyThrows
  public Professor convertToProfessor(CreateUpdateProfessorDto professorDto) {
    Professor professor = new Professor();
    professor.setId(professorDto.getId());
    professor.setFirstName(professorDto.getFirstName());
    professor.setLastName(professorDto.getLastName());
    professor.setEmail(professorDto.getEmail());
    professor.setPhone(professorDto.getPhone());
    professor.setGender(professorDto.getGender());
    professor.setUsername(professorDto.getUsername());
    professor.setBirthday(professorDto.getBirthday());
    return professor;
  }

  public CreateUpdateProfessorDto convertToProfessorDto(Professor professor) {
    CreateUpdateProfessorDto professorDto = new CreateUpdateProfessorDto();
    professorDto.setId(professorDto.getId());
    professorDto.setFirstName(professor.getFirstName());
    professorDto.setLastName(professor.getLastName());
    professorDto.setUsername(professor.getUsername());
    professorDto.setEmail(professor.getEmail());
    professorDto.setPhone(professor.getPhone());
    professorDto.setBirthday(professor.getBirthday());
    professorDto.setGender(professor.getGender());

    return professorDto;
  }


}