package com.example.demo.model.dto;

import com.example.demo.model.domain.Mentor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude={"nome", "naturalidade"})
public class MentorDTO {

    private int id;

    private String nome;

    private String naturalidade;

}
