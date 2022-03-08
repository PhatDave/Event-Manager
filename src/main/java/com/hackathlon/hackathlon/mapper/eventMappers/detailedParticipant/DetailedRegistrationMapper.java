package com.hackathlon.hackathlon.mapper.eventMappers.detailedParticipant;

import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.mapper.registrationMappers.*;
import org.mapstruct.*;

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
