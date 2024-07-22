package com.uni.university;

import com.uni.university.domain.Gender;
import com.uni.university.domain.Professor;
import com.uni.university.repository.ProfessorRepository;
import com.uni.university.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {


    private final ProfessorService professorService;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {


        professorService.findAll().forEach(professor -> System.out.println(professor));
    }


    private void updateProfessor(ProfessorRepository professorRepository) {
        long id = 1;
        Optional<Professor> professor = professorRepository.findById(id);
        professor.get().setFirstName("Giorgos");
        professor.get().setLastName("diamantakos");
        professor.get().setEmail("g@diamantakos.com");
        professor.get().setPhone("6932587410");
        professorRepository.save(professor.orElse(null));

    }

    private void findAllProfessors(ProfessorRepository professorRepository) {
        List<Professor> professors = professorRepository.findAll();
        for (Professor professor : professors) {
            System.out.println(professor);
        }

    }

    private void findProfessorById(ProfessorRepository professorRepository) {
        long id = 1;
        Optional<Professor> professor = professorRepository.findById(id);
        System.out.println(professor);
    }

    private void createProfessor(ProfessorRepository professorRepository) {
        LocalDate birthday = LocalDate.of(1980, 1, 5);
        Professor professor = new Professor("Leo", "xri", "l@x.gr", "9874561230", birthday, Gender.MALE);
        professorRepository.save(professor);
        System.out.println(professor);
    }


}

