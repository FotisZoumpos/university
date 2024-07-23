package com.uni.university;

import com.uni.university.domain.Gender;
import com.uni.university.domain.Professor;
import com.uni.university.service.ProfessorService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {


  private final ProfessorService service;


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }


  @Override
  public void run(String... args) throws Exception {

    LocalDate birthday = LocalDate.of(1993, 5, 23);
    Professor professor = new Professor("Giannis", "Davilas", "g@d.gr", "6958471203", birthday, Gender.MALE);
    //professorService.save(professor);

   /* if (service.findAll().isEmpty()) {
      System.out.println("No professors found");
    } else {
      service.findAll().forEach(System.out::println);
    }

    long id = 1;
    Optional<Professor> tempProfessor = service.findById(id);
    System.out.println(tempProfessor);*/

    service.create(professor);

  }
}

