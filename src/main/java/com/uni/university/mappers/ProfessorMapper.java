package com.uni.university.mappers;

import com.uni.university.domain.Professor;
import com.uni.university.dto.CreateUpdateProfessorDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfessorMapper {
  Professor fromDtoToProfessor(CreateUpdateProfessorDto dto);
}
