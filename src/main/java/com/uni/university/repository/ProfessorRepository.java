package com.uni.university.repository;

import com.uni.university.domain.Professor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

  List<Professor> findAllByIdIn(List<Long> ids);

  boolean existsByUsername(String username);

}