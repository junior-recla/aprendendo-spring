package com.example.demo.util;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.domain.Programa;
import com.example.demo.model.dto.AlunoDTO;
import org.springframework.context.annotation.Profile;

public class AlunoCreator {
    private static Programa programa;

    static {
        programa = new Programa();
        programa.setId(1);
        programa.setAtivo(Boolean.TRUE);
        programa.setNome("Programa associado ao Aluno");
        programa.setAnoInicio(2000);
        programa.setAnoFim(2020);
    }

    //DTO
    public static AlunoDTO createAlunoDTOToBeSaved() {
        AlunoDTO alunoDto = new AlunoDTO();
        alunoDto.setNome("Nome do Aluno");
        alunoDto.setClasse("Classe do Aluno");
        alunoDto.setProgramaId(1);
        return alunoDto;
    }

    public static AlunoDTO createValidAlunoDTO() {
        AlunoDTO alunoDto = new AlunoDTO();
        alunoDto.setId(1);
        alunoDto.setNome("Nome do Aluno");
        alunoDto.setClasse("Classe do Aluno");
        alunoDto.setProgramaId(1);
        return alunoDto;
    }

    public static AlunoDTO createValidUpdatedAlunoDTO() {
        AlunoDTO alunoDto = new AlunoDTO();
        alunoDto.setId(1);
        alunoDto.setNome("Nome do Aluno alterado");
        alunoDto.setClasse("Classe do Aluno alterado");
        alunoDto.setProgramaId(2);
        return alunoDto;
    }

    //Domain
    public static Aluno createAlunoToBeSaved() {
        Aluno alunoDto = new Aluno();
        alunoDto.setNome("Nome do Aluno");
        alunoDto.setClasse("Classe do Aluno");
        alunoDto.setPrograma(programa);
        return alunoDto;
    }

    public static Aluno createValidAluno() {
        Aluno alunoDto = new Aluno();
        alunoDto.setId(1);
        alunoDto.setNome("Nome do Aluno");
        alunoDto.setClasse("Classe do Aluno");
        alunoDto.setPrograma(programa);
        return alunoDto;
    }

    public static Aluno createValidUpdatedAluno() {
        Aluno alunoDto = new Aluno();
        alunoDto.setId(1);
        alunoDto.setNome("Nome do Aluno alterado");
        alunoDto.setClasse("Classe do Aluno alterado");
        alunoDto.setPrograma(programa);
        return alunoDto;
    }

}
