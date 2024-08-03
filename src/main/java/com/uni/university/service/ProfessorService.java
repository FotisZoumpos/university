package com.uni.university.service;

import com.uni.university.domain.Professor;
import com.uni.university.repository.ProfessorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
  public Professor create(Professor professor) {
    //TODO replace with proper DTO
    if (professor.getId() != null) {
      throw new Exception();
    }
    if (repository.existsByUsername(professor.getUsername())) {
      throw new Exception();
    }

    return save(professor);
  }

  @SneakyThrows
  public Professor update(Professor professor) {
    if (professor.getId() == null) {
      throw new Exception();
    }
    Professor existingProfessor = findOrThrow(professor.getId());

    if (professor.getFirstName() != null) {
      existingProfessor.setFirstName(professor.getFirstName());
    }
    if (professor.getLastName() != null) {
      existingProfessor.setLastName(professor.getLastName());
    }
    if (professor.getEmail() != null) {
      existingProfessor.setEmail(professor.getEmail());
    }
    if (professor.getPhone() != null) {
      existingProfessor.setPhone(professor.getPhone());
    }
    if (professor.getGender() != null) {
      existingProfessor.setGender(professor.getGender());
    }
    if (professor.getBirthday() != null) {
      existingProfessor.setBirthday(professor.getBirthday());
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

}