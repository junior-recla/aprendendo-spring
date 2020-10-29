package com.example.demo.service;


import com.example.demo.model.domain.Mentor;
import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.model.dto.MentorDTO;
import com.example.demo.model.mapper.MentorMapper;
import com.example.demo.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.model.mapper.MentorMapper.toDomain;
import static java.util.stream.Collectors.toList;

@Service
public class MentorService {

    @Autowired
    private MentorRepository mentorRepository;

    public List<MentorDTO> list(){
        return mentorRepository
                .findAllByAtivo(Boolean.TRUE)
                .stream()
                .map(MentorMapper::toDTO)
                .collect(toList());
    }

    public Optional<MentorDTO> getByIndex(Integer id) {
        return mentorRepository
                .findById(id)
                .map( MentorMapper::toDTO );
    }

    public Optional<MentorDTO> cria(MentorDTO mentorDTO) {
        return Optional.of(mentorRepository.save(toDomain(mentorDTO)))
                .map(MentorMapper::toDTO );
    }

    public void delete(Integer id){
        mentorRepository.deleteById(id);
    }
}
