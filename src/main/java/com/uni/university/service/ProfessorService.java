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


  public Professor findByPhone(String phone) {
    if (repository.findByPhone(phone) != null) {
      System.out.println("Phone " + phone + " is found.");
      return repository.findByPhone(phone);
    }
    System.out.println("Invalid phone number.");
    return null;
  }

  public List<Professor> findByFirstName(String firstName) {
    return repository.findByFirstName(firstName);
  }

  public List<Professor> findByLastName(String lastName) {
    return repository.findByLastName(lastName);
  }

  @Transactional
  public void update(Professor professor) {

    repository.save(professor);
  }

  public List<Professor> findAll() {
    return repository.findAll();
  }

  public Optional<Professor> findById(Long id) {

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


}


