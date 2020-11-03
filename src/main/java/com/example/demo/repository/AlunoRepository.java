package com.example.demo.repository;

import com.example.demo.model.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    List<Aluno> findAllByAtivo(Boolean ativo);

    Optional<Aluno> findByIdAndAtivo(Integer id, Boolean ativo);

    @Transactional
    @Modifying
//    @Query(value = "UPDATE escola.aluno SET ativo = 0 WHERE id = ?1", nativeQuery = true)
    /*@Param("id") :id*/
    @Query("UPDATE Aluno SET ativo = 0 WHERE id = ?1")
    void deleteLogicamente(Integer id);

    boolean existsByIdAndAtivo(Integer id, Boolean ativo);
}

