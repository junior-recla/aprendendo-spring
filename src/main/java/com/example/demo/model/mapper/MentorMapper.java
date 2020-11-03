package com.example.demo.model.mapper;

import com.example.demo.model.domain.Mentor;
import com.example.demo.model.dto.MentorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MentorMapper {

    public MentorDTO toDTO(Mentor mentor);

    @Mapping(target = "ativo", ignore = true)
    public Mentor toDomain(MentorDTO mentorDTO);
}
