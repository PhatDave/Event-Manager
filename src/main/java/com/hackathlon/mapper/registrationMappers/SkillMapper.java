package com.hackathlon.mapper.registrationMappers;

import com.hackathlon.dto.requests.registrationDtos.SkillRequestDto;
import com.hackathlon.dto.responses.registrationDtos.SkillResponseDto;
import com.hackathlon.entity.user.Skill;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder=true))
public interface SkillMapper {
    Skill toEntity(SkillRequestDto dto);
    SkillResponseDto toDto(Skill skill);
    List<String> map(List<Skill> value);
    default String map(Skill value) {
        return value.toString();
    }
}
