package com.example.demo.model.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"nome", "anoInicio", "anoFim", "ativo"})
@ToString
@Entity
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Integer anoInicio;

    private Integer anoFim;

    @Column(nullable = false, columnDefinition = "TINYINT(1) default 1")
    private Boolean ativo = Boolean.TRUE;;

}
