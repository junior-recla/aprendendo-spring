package com.example.demo.factory;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.domain.Programa;
import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.model.mapper.AlunoMapper;
import com.example.demo.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlunoFactory {

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private AlunoMapper alunoMapper;

    private final Boolean ATIVO = Boolean.TRUE;

    private boolean programaExiste(Integer id) {
        return programaRepository.existsByIdAndAtivo(id, ATIVO);
    }

    private boolean programaNaoExiste(Integer programaID) {
        return !programaExiste(programaID);
    }

    public Optional<Aluno> fabricaDomain(AlunoDTO alunoDTO) {

        Integer programaID = alunoDTO.getProgramaId();

        if (programaNaoExiste(programaID)) {
            return Optional.empty();
        }

        Programa programa = programaRepository.findByIdAndAtivo(programaID, ATIVO).get();

        Aluno aluno = alunoMapper.toDomain(alunoDTO);
        aluno.setPrograma(programa);

        return Optional.of(aluno);
    }
}
