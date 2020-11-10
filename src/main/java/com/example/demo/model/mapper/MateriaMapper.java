package com.example.demo.model.mapper;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.domain.Materia;
import com.example.demo.model.dto.MateriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Mapper(componentModel = "spring")
public interface MateriaMapper {

    @Mapping(target = "alunos", ignore = true)
    MateriaDTO toDTO(Materia materia);

    @Mapping(target = "alunos", ignore = true)
    Materia toDomain(MateriaDTO materiaDTO);

    default MateriaDTO toDTORelacionado(Materia materia){
        MateriaDTO materiaDTO = toDTO(materia);

        Map<Integer, String> alunos;
        alunos = materia
                .getAlunos()
                .stream()
                .collect(toMap( Aluno::getId, Aluno::getNome));

        materiaDTO.setAlunos(alunos);

        return materiaDTO;
    }

}
