package com.uni.university.service;

import com.uni.university.domain.Professor;
import com.uni.university.repository.ProfessorRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {
  private final ProfessorRepository repository;

  public List<Professor> findAll() {
    return repository.findAll();
  }
}
