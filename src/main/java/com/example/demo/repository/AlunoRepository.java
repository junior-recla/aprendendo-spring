package com.example.demo.repository;

import com.example.demo.model.domain.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    @Transactional(readOnly = true)
    Page<Aluno> findAllByAtivo(Pageable pageable, Boolean ativo);

    @Query("SELECT a FROM Aluno a JOIN FETCH a.programa WHERE a IN :alunos AND a.ativo = 1")
    List<Aluno> findAllAlunoAtivoEProgramas(List<Aluno> alunos);

    @Transactional(readOnly = true)
    @Query(value = "SELECT aluno FROM Aluno aluno WHERE ativo = 1")
    Set<Aluno> findSetAllByAtivo(Boolean ativo);

    Optional<Aluno> findByIdAndAtivo(Integer id, Boolean ativo);

    @Transactional(readOnly = true)
//    @Query(value = "SELECT ativo FROM aluno WHERE programa_id = ?1 AND ativo = 1 limit 1", nativeQuery = true)
    @Query(value = "SELECT ativo FROM Aluno a WHERE programa = :programaId AND ativo = 1")
    Boolean existsByPrograma(Integer programaId);

    @Transactional
    @Modifying
    /*@Param("id") :id*/
    @Query("UPDATE Aluno SET ativo = 0 WHERE id = ?1")
    void deleteLogicamente(Integer id);

    boolean existsByIdAndAtivo(Integer id, Boolean ativo);
}

