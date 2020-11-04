package com.example.demo.factory;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.domain.Mentor;
import com.example.demo.model.domain.Mentoria;
import com.example.demo.model.dto.MentoriaDTO;
import com.example.demo.model.mapper.MentoriaMapper;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MentoriaFactory {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentoriaMapper mentoriaMapper;

    private final Boolean ATIVO = Boolean.TRUE;

    private boolean alunoNaoExiste(Integer id) {
        return !alunoRepository.existsByIdAndAtivo(id, ATIVO);
    }

    private boolean mentorNaoExiste(Integer id) {
        return !mentorRepository.existsByIdAndAtivo(id, ATIVO);
    }

    public Optional<Mentoria> fabricaDomain(MentoriaDTO mentoriaDTO) {

        Integer alunoId = mentoriaDTO.getAlunoId();
        Integer mentorId = mentoriaDTO.getMentorId();

        if (mentorNaoExiste(mentorId) || alunoNaoExiste(alunoId)) {
            return Optional.empty();
        }

        Aluno aluno = alunoRepository.findByIdAndAtivo(alunoId, ATIVO).get();
        Mentor mentor = mentorRepository.findByIdAndAtivo(mentorId, ATIVO).get();

        Mentoria mentoria = mentoriaMapper.toDomain(mentoriaDTO);
        mentoria.setAluno(aluno);
        mentoria.setMentor(mentor);

        return Optional.of(mentoria);
    }


}
