package com.example.demo.repository;

import com.example.demo.model.domain.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {

    Set<Materia> findAllByAtivo(Boolean ativo);

    Optional<Materia> findByIdAndAtivo(Integer id, Boolean ativo);

    @Transactional
    @Modifying
    @Query("UPDATE Materia SET ativo = 0 WHERE id = ?1")
    void deleteLogicamente(Integer id);

    boolean existsByIdAndAtivo(Integer id, Boolean ativo);

}
