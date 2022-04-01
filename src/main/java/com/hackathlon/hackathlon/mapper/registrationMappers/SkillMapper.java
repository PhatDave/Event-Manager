package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.SkillRequestDto;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.SkillResponseDto;
import com.hackathlon.hackathlon.entity.user.Skill;
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
