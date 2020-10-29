package com.example.demo.model.mapper;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.dto.AlunoDTO;

public abstract class AlunoMapper {

    private final static Aluno domainEmpty =
            new Aluno(-1,
                    "Sem nome domain pois é null",
                    "Sem classe domain pois é null",
                    Boolean.FALSE
            );

    private final static AlunoDTO dtoEmpty =
            new AlunoDTO(-1,
                    "Sem nome DTO pois é null",
                    "Sem classe DTO pois é null"
            );

    public static Aluno domainEmpty() {
        return domainEmpty;
    }

    public static AlunoDTO DTOEmpty() {
        return dtoEmpty;
    }

    public static AlunoDTO toDTO(Aluno aluno) {
        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getClasse()
        );
    }

    public static Aluno toDomain(AlunoDTO alunoDTO) {
        return new Aluno(
                alunoDTO.getId(),
                alunoDTO.getNome(),
                alunoDTO.getClasse(),
                true
        );
    }
}
