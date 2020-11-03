package com.example.demo.model.mapper;

import com.example.demo.model.domain.Programa;
import com.example.demo.model.dto.ProgramaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramaMapper {

    ProgramaDTO toDTO(Programa programa);

    @Mapping(target = "ativo", ignore = true)
    Programa toDomain(ProgramaDTO programaDTO);

}
