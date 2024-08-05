package com.uni.university.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.uni.university.constraints.CreateGroup;
import com.uni.university.constraints.UpdateGroup;
import com.uni.university.domain.Gender;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;


@Data
public class ProfessorDto {


  @NotNull(groups = UpdateGroup.class)
  private Long id;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @Pattern(regexp = "^[A-Za-z]+$", groups = {CreateGroup.class, UpdateGroup.class})
  @Size(min = 2, max = 50, groups = {CreateGroup.class, UpdateGroup.class})
  private String firstName;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @Pattern(regexp = "^[A-Za-z]+$", groups = {CreateGroup.class, UpdateGroup.class})
  @Size(min = 2, max = 50, groups = {CreateGroup.class, UpdateGroup.class})
  private String lastName;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @Pattern(regexp = "^[A-Za-z]+$", groups = {CreateGroup.class, UpdateGroup.class})
  @Pattern(regexp = "^[A-Za-z0-9._-]+$", groups = {CreateGroup.class, UpdateGroup.class})
  private String username;

  @Email(groups = {CreateGroup.class, UpdateGroup.class})
  @Pattern(regexp = "^[A-Za-z0-9._-]+$", groups = {CreateGroup.class, UpdateGroup.class})
  private String email;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @Pattern(regexp = "^\\+?[0-9]+$", groups = {CreateGroup.class, UpdateGroup.class})
  private String phone;

  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate birthday;

  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private Gender gender;


}
