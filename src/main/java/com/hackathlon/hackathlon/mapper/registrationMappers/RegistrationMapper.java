package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.enums.RegistrationStatusEnum;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.*;

@Mapper(
        uses = {
                PersonalMapper.class,
                ExperienceMapper.class,
                CommentMapper.class,
        },
        builder = @Builder(disableBuilder = true)
)
public abstract class RegistrationMapper {
    @Autowired
    private ExperienceMapper experienceMapper;

    @Mapping(source = "personal", target = "user")
    public abstract Registration toEntity(RegistrationRequestDto dto);

    @Mapping(source = "user", target = "personal")
    @Mapping(source = "user.experience", target = "experience")
    public abstract RegistrationResponseDto toDto(Registration registration);

    // TODO testirati
    @Mapping(source = "tshirt", target = "registration.user.fluff.TShirt")
    public abstract void merge(InvitationRequestDto invitationRequestDto, @MappingTarget Registration registration);

    @AfterMapping
    public void mapAdditionalFields(RegistrationRequestDto dto, @MappingTarget Registration registration) {
//        mapExperience(dto, registration);

        if (!dto.getMotivation().isBlank()) {
            registration.getUser().getFluff().setMotivation(dto.getMotivation());
        }
        if (!dto.getPreferredOS().isBlank()) {
            registration.getUser().getFluff().setPreferredOS(dto.getPreferredOS());
        }
        if (dto.getExperience() != null) {
            registration.getUser().setExperience(experienceMapper.toEntity(dto.getExperience()));
            registration.getUser().getExperience().setUser(registration.getUser());
        }

        // map registrationId inside user
        registration.getUser().setRegistration(registration);
    }
}
