package com.uni.university.dto;


import com.uni.university.annotations.ValidName;
import com.uni.university.annotations.ValidPhone;
import com.uni.university.annotations.ValidUsername;
import com.uni.university.constraints.CreateGroup;
import com.uni.university.constraints.UpdateGroup;
import com.uni.university.domain.Gender;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateUpdateProfessorDto {

  @Null(groups = CreateGroup.class)
  private Long idForCreate;

  @NotNull(groups = UpdateGroup.class)
  private Long idForUpdate;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @ValidName(groups = {CreateGroup.class, UpdateGroup.class})
  private String firstName;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @ValidName(groups = {CreateGroup.class, UpdateGroup.class})
  private String lastName;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @ValidUsername(groups = {CreateGroup.class, UpdateGroup.class})
  private String username;

  @Email(groups = {CreateGroup.class, UpdateGroup.class})
  private String email;

  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @ValidPhone(groups = {CreateGroup.class, UpdateGroup.class})
  private String phone;

  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private LocalDate birthday;

  @Null(groups = {CreateGroup.class})
  private Gender gender;


}
