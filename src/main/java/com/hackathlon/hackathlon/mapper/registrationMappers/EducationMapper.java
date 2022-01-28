package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.EducationRequestDto;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.user.Education;
import org.mapstruct.*;

@Mapper(builder = @Builder(disableBuilder=true))
public interface EducationMapper {
    @Mapping(source = "year", target = "years")
    Education toEntity(EducationRequestDto dto);

    @Mapping(source = "years", target = "year")
    EducationResponseDto toDto(Education dto);
}
