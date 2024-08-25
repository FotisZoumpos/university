package com.uni.university.dto;


import com.uni.university.annotations.Name;
import com.uni.university.annotations.Phone;
import com.uni.university.annotations.Username;
import com.uni.university.constraints.CreateGroup;
import com.uni.university.constraints.UpdateGroup;
import com.uni.university.domain.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateProfessorDto {

  @Null(groups = CreateGroup.class)
  @NotNull(groups = UpdateGroup.class)
  private Long id;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @Name(groups = {CreateGroup.class, UpdateGroup.class})
  private String firstName;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @Name(groups = {CreateGroup.class, UpdateGroup.class})
  private String lastName;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @Username(groups = {CreateGroup.class, UpdateGroup.class})
  private String username;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @Email(groups = {CreateGroup.class, UpdateGroup.class})
  private String email;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @Phone(groups = {CreateGroup.class, UpdateGroup.class})
  private String phone;

  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private LocalDate birthday;

  @Null(groups = {CreateGroup.class})
  @NotNull(groups = {UpdateGroup.class})
  private Gender gender;


}
