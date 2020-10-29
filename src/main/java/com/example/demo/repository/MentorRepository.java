package com.example.demo.repository;

import com.example.demo.model.domain.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor, Integer> {
    List<Mentor> findAllByAtivo(Boolean ativo);
}