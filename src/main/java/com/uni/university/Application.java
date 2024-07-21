package com.uni.university;

import com.uni.university.domain.Gender;
import com.uni.university.domain.Professor;
import com.uni.university.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {





	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(ProfessorRepository professorRepository) {
		return runner-> {



			//createProfessor(professorRepository);
			//findProfessorById(professorRepository);
			//findAllProfessors(professorRepository);



		};
	}

	private void findAllProfessors(ProfessorRepository professorRepository) {
		List<Professor> professors = professorRepository.findAll();
		for(Professor professor : professors) {
			System.out.println(professor);
		}

	}

	private void findProfessorById(ProfessorRepository professorRepository) {
		int id = 1;
		Professor professor = professorRepository.findById(id);
		System.out.println(professor);
	}

	private void createProfessor(ProfessorRepository professorRepository) {
		LocalDate birthday = LocalDate.of(1993,12,19);
		Professor professor = new Professor("Kyriakos","Pap","k@p.gr","6754123980",birthday,Gender.MALE);
		professorRepository.save(professor);
		System.out.println(professor);
	}


}

