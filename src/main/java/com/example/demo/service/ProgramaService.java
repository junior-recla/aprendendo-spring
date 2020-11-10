package com.example.demo.service;

import com.example.demo.model.domain.Programa;
import com.example.demo.model.dto.ProgramaDTO;
import com.example.demo.model.mapper.ProgramaMapper;
import com.example.demo.repository.ProgramaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
public class ProgramaService {
    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private ProgramaMapper programaMapper;

    @Autowired
    private AlunoService alunoService;

    private final Boolean ATIVO = Boolean.TRUE;

    private final Function<Programa, ProgramaDTO> toProgramaDTO = p -> programaMapper.toDTO(p);

    private boolean programaAtivoExiste(Integer id, Boolean ativo) {
        return programaRepository.existsByIdAndAtivo(id, ATIVO);
    }

    private boolean naoAhAlunoRelacionadoAoPrograma(Integer id){
        return !alunoService.getByPrgramaIndex(id);
    }

    public List<ProgramaDTO> list() {
        return programaRepository
                .findAllByAtivo(Boolean.TRUE)
                .stream()
                .map(toProgramaDTO)
                .collect(toList());
    }

    public Optional<ProgramaDTO> getByIndex(Integer id) {
        return programaRepository
                .findByIdAndAtivo(id, ATIVO)
                .map(toProgramaDTO);
    }

    public Optional<ProgramaDTO> cria(@NonNull ProgramaDTO programaDTO) {
        return Optional.of(programaRepository.save(programaMapper.toDomain(programaDTO)))
                .map(toProgramaDTO);
    }

    public boolean delete(Integer id) {
        boolean programaExiste = programaAtivoExiste(id, ATIVO);
        boolean naoAhAlunoRelacionado = naoAhAlunoRelacionadoAoPrograma(id);

        boolean possoDeletar = programaExiste && naoAhAlunoRelacionado;

        if (possoDeletar) {
            programaRepository.deleteLogicamente(id);
        }
        //TODO lançar exception informando q há algum aluno relacionado ao programa, ou apagar em cascata e não lançar exception
        return possoDeletar;
    }

    public boolean update(Integer id, ProgramaDTO programaDTO) {
        boolean programaExiste = programaAtivoExiste(id, ATIVO);
        if (programaExiste) cria(programaDTO);
        return programaExiste;
    }

}
