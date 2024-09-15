package com.uni.university.service;

import com.uni.university.domain.Professor;
import com.uni.university.dto.CreateUpdateProfessorDto;
import com.uni.university.mappers.ProfessorMapper;
import com.uni.university.repository.ProfessorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {

  private final ProfessorRepository repository;

  private final ProfessorMapper professorMapper;
  //TODO All Exceptions should be replaced with customised university exceptions

  public List<Professor> findAll() {
    return repository.findAll();
  }

  @SneakyThrows
  public Professor findOrThrow(Long id) {
    return repository.findById(id).orElseThrow(Exception::new);
  }

  @SneakyThrows
  public Professor save(Professor professor) {
    /*TODO could save implement the existsByUsername condition instead of the create?
     *  Practically we want to check if the username exists during the save
     *  two conditions:
     *  1. new professor
     *  2. existing professor*/
    if (professor.getId() == null) {

      if (repository.existsByUsername(professor.getUsername())) {
        throw new Exception();
      }
    }

    return repository.save(professor);
  }

  // TODO maybe we could unify them into one upsert method, to check after controller
  public Professor create(CreateUpdateProfessorDto professorDto) {
    return save(professorMapper.fromDtoToProfessor(professorDto));
  }

  public Professor update(CreateUpdateProfessorDto professorDto) {
    // TODO check for id mismatch in controller
    return save(professorMapper.fromDtoToProfessor(professorDto));
  }

  public void deleteById(Long id) {
    /*Let's leave it like this for now and deal with the courses later */
    repository.deleteById(id);
  }

  public void deleteAll(List<Long> professorIds) {
    /*Let's leave it like this for now and deal with the courses later */
    repository.deleteAllByIdInBatch(professorIds);
  }
}