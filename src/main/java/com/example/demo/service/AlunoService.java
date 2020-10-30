package com.example.demo.service;

import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.model.mapper.AlunoMapper;
import com.example.demo.repository.AlunoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.demo.model.mapper.AlunoMapper.toDomain;
import static java.util.stream.Collectors.toList;

@Service
public class AlunoService {

    private final Boolean ATIVO = Boolean.TRUE;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoDTO> list() {
        return alunoRepository
                .findAllByAtivo(ATIVO)
                .stream()
                .map(AlunoMapper::toDTO)
                .collect(toList());
    }

    public Optional<AlunoDTO> getByIndex(Integer id) {
        return alunoRepository
                .findByIdAndAtivo(id, ATIVO)
                .map(AlunoMapper::toDTO);
    }

    public Optional<AlunoDTO> cria(@NonNull AlunoDTO alunoDTO) {
        return Optional
                .of(alunoRepository.save(toDomain(alunoDTO)))
                .map(AlunoMapper::toDTO);
    }

    @Transactional
    public boolean delete(Integer id) {
        boolean existe = alunoExiste(id, ATIVO);
        if (existe) alunoRepository.deleteLogicamente(id);
        return existe;
    }

    public boolean update(Integer id, @NonNull AlunoDTO alunoDTO) {
        boolean existe = alunoExiste(id, ATIVO);
        if (existe) cria(alunoDTO);
        return existe;
    }

    private boolean alunoExiste(Integer id, Boolean ativo) {
        return alunoRepository.existsByIdAndAtivo(id, ATIVO);
    }

}
