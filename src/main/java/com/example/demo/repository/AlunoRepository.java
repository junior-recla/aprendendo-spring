package com.example.demo.repository;

import com.example.demo.model.domain.Aluno;
import com.example.demo.model.dto.AlunoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    List<Aluno> findAllByAtivo(Boolean ativo);

    Optional<Aluno> findByIdAndAtivo(Integer id, Boolean ativo);

    @Modifying
//    @Query(value = "UPDATE escola.aluno SET ativo = 0 WHERE id = ?1", nativeQuery = true)
    @Query(value = "UPDATE Aluno SET ativo = 0 WHERE id = ?1")
    void deleteLogicamente(/*@Param("id")*/ Integer id);
}

