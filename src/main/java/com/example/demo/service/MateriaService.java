package com.example.demo.service;

import com.example.demo.factory.MateriaFactory;
import com.example.demo.model.domain.Materia;
import com.example.demo.model.dto.MateriaDTO;
import com.example.demo.model.mapper.MateriaMapper;
import com.example.demo.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;


@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private MateriaMapper materiaMapper;

    @Autowired
    private MateriaFactory materiaFactory;

    private final Boolean ATIVO = Boolean.TRUE;

    private final Function<Materia, MateriaDTO> toMateriaDTO = m -> materiaMapper.toDTORelacionado(m);

    private boolean materiaAtivoExiste(Integer id) {
        return materiaRepository.existsByIdAndAtivo(id, ATIVO);
    }

    public List<MateriaDTO> list() {
        List<MateriaDTO> materiasDTOList = materiaRepository
                .findAllByAtivo(ATIVO)
                .stream()
                .map(toMateriaDTO)
                .collect(toList());

        materiasDTOList.sort(comparing(MateriaDTO::getId));

        return materiasDTOList;
    }

    public Optional<MateriaDTO> getByIndex(Integer id) {
        return materiaRepository
                .findByIdAndAtivo(id, ATIVO)
                .map(toMateriaDTO);
    }

    public Optional<MateriaDTO> cria(MateriaDTO materiaDTO) {
        return materiaFactory
                .fabricaDomain(materiaDTO)
                .map(materiaRepository::save)
                .map(toMateriaDTO);
    }

    public boolean delete(Integer id) {
        boolean materiaExiste = materiaAtivoExiste(id);
        if (materiaExiste) materiaRepository.deleteLogicamente(id);
        return materiaExiste;
    }

    public boolean update(Integer id, MateriaDTO materiaDTO) {
        boolean materiaExiste = materiaAtivoExiste(id);
        if (materiaExiste) cria(materiaDTO);
        return materiaExiste;
    }
}
