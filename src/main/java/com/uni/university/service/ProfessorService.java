package com.uni.university.service;

import com.uni.university.domain.Course;
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


  public void save(Professor professor) {

    repository.save(professor);
  }

  public void create(Professor professor) {

    if (professor.getUsername() != null) {
      save(professor);
    }

  }

  public void update(Professor professor) {
    if (professor.getId() != null && repository.existsById(professor.getId())) {
      save(professor);
    }
  }

  public void deleteById(Long id) {

    if (findById(id).isPresent()) {
      Optional<Professor> professor = findById(id);
      List<Course> courses = professor.get().getCourses();
      for (Course course : courses) {
        course.setProfessor(null);
      }
      repository.deleteById(id);
    }
  }

  public void deleteAll() {
    List<Professor> professors = findAll();
    for (Professor professor : professors) {
      List<Course> courses = professor.getCourses();
      for (Course course : courses) {
        course.setProfessor(null);
      }
    }
    repository.deleteAll();
  }

}



































