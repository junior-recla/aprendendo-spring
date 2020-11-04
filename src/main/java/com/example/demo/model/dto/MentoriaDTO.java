package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentoriaDTO {

    @PositiveOrZero
    private Integer id;

    @NotNull
    @Positive
    private Integer alunoId;

    @NotNull
    @Positive
    private Integer mentorId;

}
