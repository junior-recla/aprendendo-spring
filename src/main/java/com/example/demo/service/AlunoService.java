package com.example.demo.service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.factory.AlunoFactory;
import com.example.demo.model.domain.Aluno;
import com.example.demo.model.dto.AlunoDTO;
import com.example.demo.model.mapper.AlunoMapper;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final Function<Aluno, AlunoDTO> toAlunoDTO = a -> alunoMapper.toDTO(a);

    public Page<AlunoDTO> page(Pageable pageable) {
        Page<Aluno> page = alunoRepository.findAllByAtivo(pageable, ATIVO);
        alunoRepository.findAllAlunoAtivoEProgramas(page.stream().collect(toList()));
        return page.map(toAlunoDTO);
    }

    public Optional<AlunoDTO> getByIndex(Integer id) {
        boolean alunoExiste = alunoExiste(id);
        if (alunoExiste) {
            return alunoRepository
                    .findByIdAndAtivo(id, ATIVO)
                    .map(toAlunoDTO);
        } else {
            throw new ResourceNotFoundException("Não foi possível encontrar Aluno com a id: " + id );
        }
    }

    public boolean getByPrgramaIndex(Integer id) {
        return alunoRepository.existsByPrograma(id) != null;
    }

    public Optional<AlunoDTO> cria(AlunoDTO alunoDTO) {
        return alunoFactory
                .fabricaDomain(alunoDTO)
                .map(alunoRepository::save)
                .map(toAlunoDTO);
    }

    public AlunoDTO delete(Integer id) throws ResourceNotFoundException {
        //TODO ao apagar o aluno, apagar tmb as mentorias relacionadas
        AlunoDTO alunoDTOExcluido;
        boolean alunoExiste = alunoExiste(id);
        if (alunoExiste) {
            alunoDTOExcluido = getByIndex(id).get();
            alunoRepository.deleteLogicamente(id);
            return alunoDTOExcluido;
        } else {
            throw new ResourceNotFoundException("Aluno id: " + id + " não existe! Nada foi apagado");
        }
    }

    public AlunoDTO update(Integer id, AlunoDTO alunoDTO) {
        boolean alunoExiste = alunoExiste(id);
        if (alunoExiste) {
            return cria(alunoDTO).get();
        } else {
            throw new ResourceNotFoundException("Aluno id: " + id + " não existe! Nada foi alterado");
        }
    }

    private boolean alunoExiste(Integer id) {
        return alunoRepository.existsByIdAndAtivo(id, ATIVO);
    }

}
