package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlunoDTO {

    private Integer id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String classe;

    @NotNull
    private Integer programaID;

}
