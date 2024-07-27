package com.uni.university.repository;

import com.uni.university.domain.Professor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProfessorRepository extends JpaRepository<Professor, Long> {


  void deleteAll();

  List<Professor> findByFirstName(String firstName);

  List<Professor> findByLastName(String lastName);

  Professor findByPhone(String phone);


  boolean existsByEmail(String email);

  boolean existsByPhone(String phone);
}
