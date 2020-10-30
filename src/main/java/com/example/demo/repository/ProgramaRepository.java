package com.example.demo.repository;

import com.example.demo.model.domain.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProgramaRepository extends JpaRepository<Programa, Integer> {

    List<Programa> findAllByAtivo(Boolean ativo);

    Optional<Programa> findByIdAndAtivo(Integer id, Boolean ativo);

    @Modifying
    @Query(value = "UPDATE Programa SET ativo = 0 WHERE id = ?1")
    void deleteLogicamente(Integer id);
}