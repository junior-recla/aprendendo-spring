package com.example.demo.model.mapper;

import com.example.demo.model.domain.Mentoria;
import com.example.demo.model.dto.MentoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MentoriaMapper {

    @Mapping(target = "mentorId", ignore = true)
    @Mapping(target = "alunoId", ignore = true)
    MentoriaDTO toDTO(Mentoria mentoria);

    @Mapping(target = "mentor", ignore = true)
    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    Mentoria toDomain(MentoriaDTO mentoriaDTO);

    default MentoriaDTO toDTORelacionado(Mentoria mentoria){
        MentoriaDTO mentoriaDTO = toDTO(mentoria);

        mentoriaDTO.setAlunoId(mentoria.getAluno().getId());
        mentoriaDTO.setMentorId(mentoria.getMentor().getId());

        return mentoriaDTO;
    }
}
