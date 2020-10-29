package com.example.demo.repository;

import com.example.demo.model.domain.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramaRepository extends JpaRepository<Programa, Integer> {
    List<Programa> findAllByAtivo(Boolean ativo);
}