package com.example.demo.service;

import com.example.demo.factory.MentoriaFactory;
import com.example.demo.model.domain.Mentoria;
import com.example.demo.model.dto.MentoriaDTO;
import com.example.demo.model.mapper.MentoriaMapper;
import com.example.demo.repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
public class MentoriaService {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @Autowired
    private MentoriaMapper mentoriaMapper;

    @Autowired
    private MentoriaFactory mentoriaFactory;

    private final Boolean ATIVO = Boolean.TRUE;

    private final Function<Mentoria, MentoriaDTO> toMentoriaDTO = m -> mentoriaMapper.toDTORelacionado(m);

    private boolean mentoriaAtivoExiste(Integer id) {
        return mentoriaRepository.existsByIdAndAtivo(id, ATIVO);
    }

    public List<MentoriaDTO> list() {
        return mentoriaRepository
                .findAllByAtivo(ATIVO)
                .stream()
                .map(toMentoriaDTO)
                .collect(toList());
    }

    public Optional<MentoriaDTO> getByIndex(Integer id) {
        return mentoriaRepository
                .findByIdAndAtivo(id, ATIVO)
                .map(toMentoriaDTO);
    }

    public Optional<MentoriaDTO> cria(MentoriaDTO mentoriaDTO) {
        return mentoriaFactory
                .fabricaDomain(mentoriaDTO)
                .map(mentoriaRepository::save)
                .map(toMentoriaDTO);
    }

    public boolean delete(Integer id) {
        boolean mentoriaExiste = mentoriaAtivoExiste(id);
        if (mentoriaExiste) mentoriaRepository.deleteLogicamente(id);
        return mentoriaExiste;
    }

    public boolean update(Integer id, MentoriaDTO mentoriaDTO) {
        boolean mentoriaExiste = mentoriaAtivoExiste(id);
        if (mentoriaExiste) cria(mentoriaDTO);
        return mentoriaExiste;
    }

}
