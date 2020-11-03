package com.example.demo.repository;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.domain.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Integer> {

    List<Mentor> findAllByAtivo(Boolean ativo);

    Optional<Mentor> findByIdAndAtivo(Integer id, Boolean ativo);

    @Transactional
    @Modifying
    @Query("UPDATE Mentor SET ativo = 0 WHERE id = ?1")
    void deleteLogicamente(Integer id);

    boolean existsByIdAndAtivo(Integer id, Boolean ativo);
}