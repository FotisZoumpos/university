package com.uni.university.service;

import com.uni.university.constraints.CreateGroup;
import com.uni.university.domain.Professor;
import com.uni.university.dto.CreateUpdateProfessorDto;
import com.uni.university.repository.ProfessorRepository;
import jakarta.validation.Valid;
import java.util.List;
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
  public Professor create(@Valid CreateUpdateProfessorDto professorDto) {

    if (repository.existsByUsername(professorDto.getUsername())) {
      throw new Exception();
    }

    Professor professor = convertToProfessor(professorDto);

    return repository.save(professor);
  }

  @SneakyThrows
  public Professor update(CreateUpdateProfessorDto professorDto) {
   
    Professor existingProfessor = findOrThrow(professorDto.getId());
    if (professorDto.getFirstName() != null && !professorDto.getFirstName().equals(existingProfessor.getFirstName())) {
      existingProfessor.setFirstName(professorDto.getFirstName());
    } else {
      throw new Exception();
    }
    if (professorDto.getLastName() != null && !professorDto.getLastName().equals(existingProfessor.getLastName())) {
      existingProfessor.setLastName(professorDto.getLastName());
    } else {
      throw new Exception();
    }
    if (professorDto.getUsername() != null && !professorDto.getUsername().equals(existingProfessor.getUsername())) {
      existingProfessor.setUsername(professorDto.getUsername());
    } else {
      throw new Exception();
    }
    if (professorDto.getEmail() != null && !professorDto.getEmail().equals(existingProfessor.getEmail())) {
      existingProfessor.setEmail(professorDto.getEmail());
    } else {
      throw new Exception();
    }
    if (professorDto.getPhone() != null && !professorDto.getPhone().equals(existingProfessor.getPhone())) {
      existingProfessor.setPhone(professorDto.getPhone());
    } else {
      throw new Exception();
    }
    if (professorDto.getGender() != null && !professorDto.getGender().equals(existingProfessor.getGender())) {
      existingProfessor.setGender(professorDto.getGender());
    } else {
      throw new Exception();
    }
    if (professorDto.getBirthday() != null && !professorDto.getBirthday().equals(existingProfessor.getBirthday())) {
      existingProfessor.setBirthday(professorDto.getBirthday());
    } else {
      throw new Exception();
    }

    return save(existingProfessor);
  }

  public void deleteById(Long id) {
    /*Let's leave it like this for now and deal with the courses later */
    repository.deleteById(id);
  }

  public void deleteAll(List<Long> professorIds) {
    /*Let's leave it like this for now and deal with the courses later */
    repository.deleteAllByIdInBatch(professorIds);
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