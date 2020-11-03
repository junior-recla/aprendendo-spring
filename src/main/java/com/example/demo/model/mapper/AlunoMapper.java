package com.example.demo.model.mapper;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.dto.AlunoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    //AlunoMaaper INSTANCE = Mappers.getMapper(AlunoMaaper.class);

    @Mapping(target = "programaID", ignore = true)
    AlunoDTO toDTO(Aluno aluno);

    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "programa", ignore = true)
    Aluno toDomain(AlunoDTO alunoDTO);

    default AlunoDTO toDTOrelacionado(Aluno aluno) {
        AlunoDTO alunoDTO = toDTO(aluno);

//        alunoDTO.setId(aluno.getId());
        alunoDTO.setProgramaID(aluno.getPrograma().getId());

        return alunoDTO;
    }

}
