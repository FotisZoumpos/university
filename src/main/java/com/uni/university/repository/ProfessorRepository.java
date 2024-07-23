package com.uni.university.repository;

import com.uni.university.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProfessorRepository extends JpaRepository<Professor, Long> {



    public List<Professor> findAll();
    Professor findById(Integer id);
    Professor save(Professor professor);
   // Professor create(Professor professor);
   // Professor update(Professor professor);
    void deleteById(Integer id);
    void deleteAll();
    void findBy();


  boolean existsByEmail(String email);

  boolean existsByPhone(String phone);
}
