package com.example.demo.model.dto;

import com.example.demo.model.domain.Programa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramaDTO {

    private int id;

    private String nome;

    private int anoInicio;

    private int anoFim;

}
