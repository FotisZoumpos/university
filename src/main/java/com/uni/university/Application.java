package com.uni.university;

import com.uni.university.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {


  private final ProfessorService professorService;


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

  }

  @Override
  public void run(String... args) throws Exception {

    // long id = 1;
    // Optional<Professor> tempProfessor = professorService.findById(id);
    // System.out.println(tempProfessor);
    // professorService.findAll().forEach(System.out::println);

    /* LocalDate birthday = LocalDate.of(1993, 4, 4);
      Professor professor = new Professor("Fotis", "Zoumpos", "f@z.gr", "6980972601", birthday, Gender.MALE);
      professorService.create(professor);

      LocalDate birthday1 = LocalDate.of(2000, 10, 10);
      Professor professor1 = new Professor("Giannis", "Davilas", "g@d.gr", "6954123870", birthday1, Gender.MALE);
      professorService.create(professor1);*/

    //long id = 10;
    //Optional<Professor> tempProfessor = professorService.deleteById(id);

    professorService.deleteAll();
  }


}

