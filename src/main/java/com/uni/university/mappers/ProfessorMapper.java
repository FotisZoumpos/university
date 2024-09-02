package com.uni.university.mappers;

import com.uni.university.domain.Professor;
import com.uni.university.dto.CreateUpdateProfessorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfessorMapper {
  ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);

  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "username", target = "username")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "phone", target = "phone")
  @Mapping(source = "birthday", target = "birthday")
  @Mapping(source = "gender", target = "gender")
  @Mapping(target = "courses", ignore = true)
  Professor professorDtoToProfessor(CreateUpdateProfessorDto dto);

  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "username", target = "username")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "phone", target = "phone")
  @Mapping(source = "birthday", target = "birthday")
  @Mapping(source = "gender", target = "gender")
  @Mapping(target = "courses", ignore = true)
  CreateUpdateProfessorDto professorToDto(Professor professor);
}
