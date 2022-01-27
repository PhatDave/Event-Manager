package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.user.*;
import org.mapstruct.*;

@Mapper(
        uses = {
                SkillMapper.class,
        },
        builder = @Builder(disableBuilder=true)
)
public interface ExperienceMapper {
    Experience toEntity(ExperienceRequestDto dto);
}
