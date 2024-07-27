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

   
  }


}

