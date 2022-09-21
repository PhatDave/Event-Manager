package com.hackathlon.mapper.eventMappers.detailedParticipant;

import com.hackathlon.dto.responses.registrationDtos.RegistrationResponseDto;
import com.hackathlon.entity.Registration;
import com.hackathlon.mapper.registrationMappers.CommentMapper;
import com.hackathlon.mapper.registrationMappers.ExperienceMapper;
import com.hackathlon.mapper.registrationMappers.PersonalMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        uses = {
                PersonalMapper.class,
                ExperienceMapper.class,
                CommentMapper.class,
        },
        builder = @Builder(disableBuilder = true)
)
public interface DetailedRegistrationMapper {
    @Mapping(source="registration.user", target="personal")
    @Mapping(source="registration.comments", target="comments")
    @Mapping(source="registration.user.experience", target="experience")
    RegistrationResponseDto toDto(Registration registration);
}
