package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.User.*;
import org.mapstruct.*;

@Mapper(builder = @Builder(disableBuilder=true))
public interface EducationMapper {
    Education toEntity(EducationRequestDto dto);
}
