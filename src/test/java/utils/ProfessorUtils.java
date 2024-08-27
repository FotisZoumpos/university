package utils;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import com.uni.university.domain.Gender;
import com.uni.university.domain.Professor;
import com.uni.university.dto.CreateUpdateProfessorDto;
import java.time.LocalDate;
import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfessorUtils {
  public static Professor getRandomProfessor() {
    return Professor.builder()
        .id(new Random().nextLong())
        .username(randomAlphabetic(10))
        .firstName(randomAlphabetic(10))
        .lastName(randomAlphabetic(10))
        .email(getRandomEmail())
        .phone(getRandomPhone())
        .gender(Gender.values()[new Random().nextInt(Gender.values().length)])
        .birthday(LocalDate.of(1990, 10, 8))
        .build();
  }

  public static CreateUpdateProfessorDto getRandomProfessorDto() {
    return CreateUpdateProfessorDto.builder()
        .id(new Random().nextLong())
        .username(randomAlphabetic(10))
        .firstName(randomAlphabetic(10))
        .lastName(randomAlphabetic(10))
        .email(getRandomEmail())
        .phone(getRandomPhone())
        .gender(Gender.values()[new Random().nextInt(Gender.values().length)])
        .birthday(LocalDate.of(1990, 10, 8))
        .build();
  }

  public static CreateUpdateProfessorDto getRandomProfessorDto(Long id) {
    return CreateUpdateProfessorDto.builder()
        .id(id)
        .username(randomAlphabetic(10))
        .firstName(randomAlphabetic(10))
        .lastName(randomAlphabetic(10))
        .email(getRandomEmail())
        .phone(getRandomPhone())
        .gender(Gender.values()[new Random().nextInt(Gender.values().length)])
        .birthday(LocalDate.of(2000, 4, 4))
        .build();
  }

  public static String getRandomEmail() {
    return randomAlphabetic(5) + "@" + randomAlphabetic(5);
  }

  public static String getRandomPhone() {
    return "+30697" + randomNumeric(7);
  }
}
