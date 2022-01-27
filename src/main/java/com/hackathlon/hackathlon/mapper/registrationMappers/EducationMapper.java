package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.EducationRequestDto;
import com.hackathlon.hackathlon.entity.user.Education;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder=true))
public interface EducationMapper {
    Education toEntity(EducationRequestDto dto);

    EducationRequestDto toDto(Education dto);
}
