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

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoDTO> list() {
        return alunoRepository
                .findAllByAtivo(Boolean.TRUE)
                .stream()
                .map(AlunoMapper::toDTO)
                .collect(toList());
    }

    public Optional<AlunoDTO> getByIndex(Integer id) {
        return alunoRepository
                .findByIdAndAtivo(id, Boolean.TRUE)
                .map(AlunoMapper::toDTO);
    }

    public Optional<AlunoDTO> cria(@NonNull AlunoDTO alunoDTO) {
        return Optional.of(alunoRepository.save(toDomain(alunoDTO)))
                .map(AlunoMapper::toDTO);
    }

    @Transactional
    public void delete(Integer id) {
        alunoRepository.deleteLogicamente(id);
    }
}
