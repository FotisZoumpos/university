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

}


