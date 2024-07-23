package com.uni.university.service;

import com.uni.university.domain.Course;
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
  public Optional<Professor> deleteById(long id) {
    Professor professor = repository.findById(id).orElse(null);
    if (professor != null) {
      List<Course> courses = professor.getCourses();

      for (Course course : courses) {
        course.setProfessor(null);
      }
      repository.delete(professor);
      System.out.println("Professor deleted: " + professor);
      return Optional.of(professor);
    } else {
      System.out.println("Professor with ID:" + id + " not found.");
      return Optional.empty();

    }
  }

  @Transactional
  public void deleteAll() {
    List<Professor> professors = findAll();
    for (Professor professor : professors) {
      List<Course> courses = professor.getCourses();
      for (Course course : courses) {
        course.setProfessor(null);
      }
      repository.delete(professor);
    }
    System.out.println("Professors deleted successfully.");
  }

  @Transactional
  public void create(Professor professor) {


    if (exists(professor)) {
      System.out.println("Professor " + professor.getFirstName() + " " + professor.getLastName() + " already exists.");
    } else {
      repository.save(professor);
      System.out.println("Professor created: " + professor.getFirstName() + " " + professor.getLastName());
    }

  }

  public boolean exists(Professor professor) {

    boolean emailExists = repository.existsByEmail(professor.getEmail());
    boolean phoneExists = repository.existsByPhone(professor.getPhone());
    return emailExists || phoneExists;

  }
}
