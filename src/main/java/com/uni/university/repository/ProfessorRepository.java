package com.uni.university.repository;

import com.uni.university.domain.Professor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProfessorRepository extends JpaRepository<Professor, Long> {


  public List<Professor> findAll();

  Professor save(Professor professor);

  // Professor create(Professor professor);
  // Professor update(Professor professor);
  void deleteById(Integer id);

  void deleteAll();

  void findBy();


  boolean existsByEmail(String email);

  boolean existsByPhone(String phone);
}
