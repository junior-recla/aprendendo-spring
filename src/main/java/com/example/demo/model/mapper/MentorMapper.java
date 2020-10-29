package com.example.demo.model.mapper;

import com.example.demo.model.domain.Mentor;
import com.example.demo.model.dto.MentorDTO;

public abstract class MentorMapper {

    private final static Mentor mentorEmpty =
            new Mentor(-1,
                    "Sem nome pois é null",
                    "Sem naturalidade pois é null",
                    Boolean.FALSE
            );

    private final static MentorDTO mentorDTO =
            new MentorDTO(-1,
                    "Sem nome pois é null",
                    "Sem naturalidade pois é null"
            );

    public static Mentor domainEmpty() {
        return mentorEmpty;
    }

    public static MentorDTO DTOEmpty() {
        return mentorDTO;
    }

    public static MentorDTO toDTO(Mentor mentor) {
        return new MentorDTO(
                mentor.getId(),
                mentor.getNome(),
                mentor.getNaturalidade()
        );
    }

    public static Mentor toDomain(MentorDTO mentorDTO) {
        return new Mentor(
                mentorDTO.getId(),
                mentorDTO.getNome(),
                mentorDTO.getNaturalidade(),
                Boolean.TRUE
        );
    }
}
