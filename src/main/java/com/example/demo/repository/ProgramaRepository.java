package com.example.demo.repository;

import com.example.demo.model.domain.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProgramaRepository extends JpaRepository<Programa, Integer> {

    List<Programa> findAllByAtivo(Boolean ativo);

    Optional<Programa> findByIdAndAtivo(Integer id, Boolean ativo);

    @Transactional
    @Modifying
    @Query(value =
            "UPDATE programa p, aluno a " +
            "SET p.ativo = 0, a.ativo = 0 " +
            "WHERE p.id = ?1 AND a.programa_id = ?1", nativeQuery = true)
    void deleteLogicamente(Integer id);

    boolean existsByIdAndAtivo(Integer id, Boolean ativo);
}