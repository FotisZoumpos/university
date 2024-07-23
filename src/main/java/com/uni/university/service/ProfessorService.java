package com.uni.university.service;

import com.uni.university.domain.Professor;
import com.uni.university.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
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

  public Optional<Professor> findById(long id) {
    return repository.findById(id);
  }

  @Transactional
  public void create(Professor professor) {


    professor.setCourses(null);
    if (exists(professor)) {
      throw new IllegalArgumentException("Professor already exists");
    } else {
      repository.save(professor);
    }
    System.out.println("Professor created: " + professor.getFirstName() + " " + professor.getLastName());
  }

  public boolean exists(Professor professor) {


    boolean emailExists = repository.existsByEmail(professor.getEmail());
    boolean phoneExists = repository.existsByPhone(professor.getPhone());
   /* if (emailExists) {
      System.out.println("Professor with email " + professor.getEmail() + " already exists");
    } else if (phoneExists) {
      System.out.println("Professor with phone " + professor.getPhone() + " already exists");
    }*/
    return emailExists || phoneExists;
  }
}
