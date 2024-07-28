package com.uni.university.service;

import com.uni.university.domain.Professor;
import com.uni.university.repository.ProfessorRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {
  private final ProfessorRepository repository;


  public List<Professor> findAll() {
    return repository.findAll();
  }

  public Optional<Professor> findById(Long id) {

    return repository.findById(id);

  }


  public void save(Professor professor) {
    String email = professor.getEmail();
    String phone = professor.getPhone();

    if (repository.existsByEmail(email) || repository.existsByPhone(phone)) {
      System.out.println("Professor already exists.");
    } else {
      repository.save(professor);
      System.out.println("Professor successfully created.");
    }
  }

  public void create(Professor professor) {
    String email = professor.getEmail();
    String phone = professor.getPhone();

    if (professor.getId() != null || repository.existsByEmail(email) || repository.existsByPhone(phone)) {
      System.out.println("Professor already exists.");
    } else {
      repository.save(professor);
      System.out.println("Professor successfully created.");
    }
  }

}



































