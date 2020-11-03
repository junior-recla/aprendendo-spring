package com.example.demo.model.dto;

import com.example.demo.model.domain.Programa;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProgramaDTO {

    private Integer id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    private Integer anoInicio;

    @NotNull
    private Integer anoFim;

}
