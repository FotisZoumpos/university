package com.uni.university.repository;

import com.uni.university.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProfessorRepository extends JpaRepository<Professor, Long> {


  // Professor update(Professor professor);


  void deleteAll();

  void findBy();


  boolean existsByEmail(String email);

  boolean existsByPhone(String phone);
}
