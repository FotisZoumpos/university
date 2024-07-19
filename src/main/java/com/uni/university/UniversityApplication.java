package com.uni.university;

import com.uni.university.domain.Professor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	public CommandLineRunner commandLineRunner() {
		return runner-> {




		};
	}
}
