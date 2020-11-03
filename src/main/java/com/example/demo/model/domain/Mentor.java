package com.example.demo.model.domain;

import com.example.demo.model.dto.MentorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude={"nome", "naturalidade"})
@Entity
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String naturalidade;

    @Column(nullable = false,  columnDefinition = "TINYINT(1) default 1")
    private Boolean ativo = Boolean.TRUE;


}
