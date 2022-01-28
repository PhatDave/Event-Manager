package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.user.*;
import org.mapstruct.*;

@Mapper(builder = @Builder(disableBuilder=true))
public interface SkillMapper {
    Skill toEntity(SkillRequestDto dto);
    SkillResponseDto toDto(Skill skill);
}
