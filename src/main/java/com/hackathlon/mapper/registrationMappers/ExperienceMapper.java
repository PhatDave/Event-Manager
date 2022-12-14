package com.hackathlon.mapper.registrationMappers;

import com.hackathlon.dto.requests.registrationDtos.ExperienceRequestDto;
import com.hackathlon.dto.responses.registrationDtos.ExperienceResponseDto;
import com.hackathlon.entity.user.Experience;
import org.mapstruct.*;

@Mapper(
        uses={
                SkillMapper.class,
        },
        builder=@Builder(disableBuilder=true)
)
public interface ExperienceMapper {
    Experience toEntity(ExperienceRequestDto dto);

    @Mapping(source="skills", target="skills")
    ExperienceResponseDto toDto(Experience experience);

    @AfterMapping
    default void mapExperienceIdInSkill(@MappingTarget Experience experience) {
        experience.getSkills().forEach(skill -> skill.setExperience(experience));
    }
}
