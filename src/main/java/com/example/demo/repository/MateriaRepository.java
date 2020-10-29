package com.example.demo.repository;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.domain.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    List<Materia> findAllByAtivo(Boolean ativo);

}
