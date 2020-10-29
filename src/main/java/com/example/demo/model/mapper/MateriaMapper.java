package com.example.demo.model.mapper;

import com.example.demo.model.domain.Materia;
import com.example.demo.model.dto.MateriaDTO;

public abstract class MateriaMapper {

    private final static Materia materiaEmpty =
            new Materia(-1,
                    "Matéria sem nome pois é Null",
                    Boolean.FALSE
            );

    private final static MateriaDTO materiaDTOEmpty =
            new MateriaDTO(-1,
                    "Matéria sem nome pois é Null"
            );

    public static Materia domainEmpty() {
        return materiaEmpty;
    }

    public static MateriaDTO DTOEmpty() {
        return materiaDTOEmpty;
    }

    public static MateriaDTO toDTO(Materia materia) {
        return new MateriaDTO(
                materia.getId(),
                materia.getNome()
        );
    }

    public static Materia toDomain(MateriaDTO materiaDTO) {
        return new Materia(
                materiaDTO.getId(),
                materiaDTO.getNome(),
                Boolean.TRUE
        );
    }

}
