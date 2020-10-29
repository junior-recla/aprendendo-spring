package com.example.demo.model.mapper;

import com.example.demo.model.domain.Programa;
import com.example.demo.model.dto.ProgramaDTO;

import javax.persistence.Column;

public abstract class ProgramaMapper {
    private final static Programa programaDomain =
            new Programa(
                    -1,
                    "Programa sem nome pois é nulo",
                    0,
                    0,
                    Boolean.TRUE
            );

    private final static ProgramaDTO programaDTO =
            new ProgramaDTO(
                    -1,
                    "Sem nome pois é null",
                    0,
                    0
            );

    public static Programa domainEmpty(){
        return programaDomain;
    }

    public static ProgramaDTO DTOEmpty(){
        return programaDTO;
    }

    public static ProgramaDTO toDTO(Programa programa){
        return new ProgramaDTO(
                programa.getId(),
                programa.getNome(),
                programa.getAnoInicio(),
                programa.getAnoFim()
        );
    }

    public static Programa toDomain(ProgramaDTO programaDTO){
        return new Programa(
                programaDTO.getId(),
                programaDTO.getNome(),
                programaDTO.getAnoInicio(),
                programaDTO.getAnoFim(),
                Boolean.TRUE
        );
    }

}
