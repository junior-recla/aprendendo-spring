package com.example.demo.service;


import com.example.demo.model.domain.Aluno;
import com.example.demo.model.domain.Mentor;
import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.model.dto.MentorDTO;
import com.example.demo.model.mapper.MentorMapper;
import com.example.demo.repository.MentorRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
public class MentorService {

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentorMapper mentorMapper;

    private final Boolean ATIVO = Boolean.TRUE;

    private final Function<Mentor, MentorDTO> toMentorDTO = a -> mentorMapper.toDTO(a);

    private boolean mentorAtivoExiste(Integer id) {
        return mentorRepository.existsByIdAndAtivo(id, ATIVO);
    }

    public List<MentorDTO> list(){
        return mentorRepository
                .findAllByAtivo(Boolean.TRUE)
                .stream()
                .map(toMentorDTO)
                .collect(toList());
    }

    public Optional<MentorDTO> getByIndex(Integer id) {
        return mentorRepository
                .findByIdAndAtivo(id, ATIVO)
                .map(toMentorDTO);
    }

    public Optional<MentorDTO> cria(MentorDTO mentorDTO) {
        return Optional.of(mentorRepository.save(mentorMapper.toDomain(mentorDTO)))
                .map(toMentorDTO);
    }

    public boolean delete(Integer id){
        //TODO ao apagar o mentor, apagar tmb as mentorias relacionadas
        boolean mentorExiste = mentorAtivoExiste(id);
        if (mentorExiste) mentorRepository.deleteLogicamente(id);
        return mentorExiste;
    }

    public boolean update(Integer id, MentorDTO mentorDTO){
        boolean mentorExiste = mentorAtivoExiste(id);
        if (mentorExiste) cria(mentorDTO);
        return mentorExiste;
    }
}
