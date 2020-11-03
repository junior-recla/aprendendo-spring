package com.example.demo.service;

import com.example.demo.factory.AlunoFactory;
import com.example.demo.model.domain.Aluno;
import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.model.mapper.AlunoMapper;
import com.example.demo.repository.AlunoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoMapper alunoMapper;

    @Autowired
    private AlunoFactory alunoFactory;

    private final Boolean ATIVO = Boolean.TRUE;

    private final Function<Aluno, AlunoDTO> toAlunoDTO = a -> alunoMapper.toDTOrelacionado(a);

    private boolean alunoAtivoExiste(Integer id) {
        return alunoRepository.existsByIdAndAtivo(id, ATIVO);
    }

    public List<AlunoDTO> list() {
        return alunoRepository
                .findAllByAtivo(ATIVO)
                .stream()
                .map(toAlunoDTO)
                .collect(toList());
    }

    public Optional<AlunoDTO> getByIndex(Integer id) {
        return alunoRepository
                .findByIdAndAtivo(id, ATIVO)
                .map(toAlunoDTO);
    }

    public Optional<AlunoDTO> cria(@NonNull AlunoDTO alunoDTO) {
        return alunoFactory
                .fabricaDomain(alunoDTO)
                .map(alunoRepository::save)
                .map(toAlunoDTO);
    }

    public boolean delete(Integer id) {
        boolean alunoExiste = alunoAtivoExiste(id);
        if (alunoExiste) alunoRepository.deleteLogicamente(id);
        return alunoExiste;
    }

    public boolean update(Integer id, @NonNull AlunoDTO alunoDTO) {
        boolean alunoExiste = alunoAtivoExiste(id);
        if (alunoExiste) cria(alunoDTO);
        return alunoExiste;
    }

}
