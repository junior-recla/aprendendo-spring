package com.example.demo.model.mapper;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.dto.AlunoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    //AlunoMaaper INSTANCE = Mappers.getMapper(AlunoMaaper.class);

    @Mapping(target = "programaId", ignore = true)
    AlunoDTO toDTO(Aluno aluno);

    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "programa", ignore = true)
    Aluno toDomain(AlunoDTO alunoDTO);

    default AlunoDTO toDTORelacionado(Aluno aluno) {
        AlunoDTO alunoDTO = toDTO(aluno);

        alunoDTO.setProgramaId(aluno.getPrograma().getId());

        return alunoDTO;
    }

}
