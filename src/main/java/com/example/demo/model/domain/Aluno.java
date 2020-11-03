package com.example.demo.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * @Getter @Setter @NoArgsConstructor
 * @ToString(exclude="classe")
 * @EqualsAndHashCode(exclude={"firstName", "lastName", "gender"})
 * @Data @AllArgsConstructor
 * */

/*
 * @GeneratedValue(generator = "inc")
 * @GenericGenerator(name = "inc", strategy = "increment")
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"nome", "classe", "ativo"})
@ToString
@Entity
public class Aluno {
    //usar LocalDate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String classe;

    @ManyToOne
    private Programa programa;

    @Column(nullable = false,  columnDefinition = "TINYINT(1) default 1")
    private Boolean ativo = Boolean.TRUE;

//    @ManyToOne(cascade=CascadeType.PERSIST)
//    @JoinColumn(name = "id_mentor")
//    private Mentor mentor;
//
//    @ManyToOne(cascade=CascadeType.PERSIST)
//    @JoinColumn(name = "id_programa")

//    private Programa programa;

}

/*
* Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 13
Server version: 8.0.21 MySQL Community Server - GPL
* ctrl + alt + o -> apagar imports não usados
* ctrl + alt + l -> auto format
* ctrl + f2 -> para a aplicação
* shift + f6 -> refatora variavel
* shift + f10 -> roda projeto
* alt + setas -> navega entre abas
* Alt + Shift + Insert: Alterna entre modo de seleção de linha/coluna
* Alt + Shift + setas -> volta histórico do curso entre classes
*/
