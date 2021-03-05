package com.example.demo.model.domain;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * @GeneratedValue(generator = "inc")
 * @GenericGenerator(name = "inc", strategy = "increment")
 * */

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
//    @JoinColumn(name = "id_programa")

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
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
