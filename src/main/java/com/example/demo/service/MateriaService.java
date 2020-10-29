package com.example.demo.service;

import com.example.demo.model.dto.MateriaDTO;
import com.example.demo.model.mapper.MateriaMapper;
import com.example.demo.repository.MateriaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.model.mapper.MateriaMapper.toDomain;
import static java.util.stream.Collectors.toList;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<MateriaDTO> list() {
        return materiaRepository
                .findAllByAtivo(Boolean.TRUE)
                .stream()
                .map(MateriaMapper::toDTO)
                .collect(toList());
    }

    public Optional<MateriaDTO> getByIndex(int id) {
        return materiaRepository
                .findById(id)
                .map(MateriaMapper::toDTO);
    }

    public Optional<MateriaDTO> cria(@NonNull MateriaDTO materiaDTO) {
        return Optional.of(materiaRepository.save(toDomain(materiaDTO)))
                .map(MateriaMapper::toDTO);
    }

    public void delete(int id) {
        materiaRepository.deleteById(id);
    }
}
