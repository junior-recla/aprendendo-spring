package com.example.demo.factory;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.domain.Materia;
import com.example.demo.model.dto.MateriaDTO;
import com.example.demo.model.mapper.MateriaMapper;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
public class MateriaFactory {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MateriaMapper materiaMapper;

    private final Boolean ATIVO = Boolean.TRUE;

    private boolean algumAlunoNaoExiste(Set<Integer> keys) {
        /*
         * Stream allMatch() Retorna true se todos os elementos desse fluxo correspondem ao predicado fornecido.
         * Stream noneMatch() Retorna true se nem um dos elemento do fluxo corresponder ao predicado fornecido.
         * Stream anyMatch() Retorna true se pelo menos um elemento desse fluxo corresponder ao predicado fornecido.
         * */
        return keys
                .stream()
                .anyMatch(chave -> alunoRepository.findByIdAndAtivo(chave, ATIVO).isEmpty());
    }

    public Optional<Materia> fabricaDomain(MateriaDTO materiaDTO) {

        Set<Integer> keys = materiaDTO.getAlunos().keySet();

        if (algumAlunoNaoExiste(keys) || keys.isEmpty()) {
            return Optional.empty();
        }

        Set<Aluno> alunos;
        alunos = keys
                .stream()
                .map(key -> alunoRepository.findByIdAndAtivo(key, ATIVO).get())
                .collect(toSet());

        Materia materia = materiaMapper.toDomain(materiaDTO);
        materia.setAlunos(alunos);

        return Optional.of(materia);
    }
    /*
* {
	"nome": "Nome matéria",
	"alunos": {
    "8": "Nome aluno",
    "9": "Nome aluno",
    "10": "Nome aluno",
    "11": "Nome aluno"
  }
}
* */
}
