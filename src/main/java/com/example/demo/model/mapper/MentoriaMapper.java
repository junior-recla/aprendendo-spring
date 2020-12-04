package com.example.demo.model.mapper;

import com.example.demo.model.domain.Mentoria;
import com.example.demo.model.dto.MentoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MentoriaMapper {

    @Mapping(target = "mentorId", source = "mentor.id")
    @Mapping(target = "alunoId", source = "aluno.id")
    MentoriaDTO toDTO(Mentoria mentoria);

    @Mapping(target = "mentor.id", source = "mentorId")
    @Mapping(target = "aluno.id", source = "alunoId")
    @Mapping(target = "ativo", ignore = true)
    Mentoria toDomain(MentoriaDTO mentoriaDTO);

}
