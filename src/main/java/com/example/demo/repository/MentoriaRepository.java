package com.example.demo.repository;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.domain.Mentoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MentoriaRepository extends JpaRepository<Mentoria, Integer> {

    List<Mentoria> findAllByAtivo(Boolean ativo);

    Optional<Mentoria> findByIdAndAtivo(Integer id, Boolean ativo);

    @Transactional
    @Modifying
    @Query("UPDATE Mentoria SET ativo = 0 WHERE id = ?1")
    void deleteLogicamente(Integer id);

    boolean existsByIdAndAtivo(Integer id, Boolean ativo);

}
