package com.example.demo.service;

import com.example.demo.model.dto.ProgramaDTO;
import com.example.demo.model.mapper.ProgramaMapper;
import com.example.demo.repository.ProgramaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.model.mapper.ProgramaMapper.toDomain;
import static java.util.stream.Collectors.toList;

@Service
public class ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    public List<ProgramaDTO> list() {
        return programaRepository
                .findAllByAtivo(Boolean.TRUE)
                .stream()
                .map(ProgramaMapper::toDTO)
                .collect(toList());
    }

    public Optional<ProgramaDTO> getByIndex(Integer id) {
        return programaRepository
                .findByIdAndAtivo(id, Boolean.TRUE)
                .map(ProgramaMapper::toDTO);
    }

    public Optional<ProgramaDTO> cria(@NonNull ProgramaDTO programaDTO) {
        return Optional.of(programaRepository.save(toDomain(programaDTO)))
                .map(ProgramaMapper::toDTO);
    }

    public void delete(Integer id) {
        programaRepository.deleteLogicamente(id);
    }
}
