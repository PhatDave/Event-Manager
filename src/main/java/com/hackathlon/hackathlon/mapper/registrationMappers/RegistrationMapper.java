package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.*;

@Mapper(
        uses = {
                PersonalMapper.class,
        },
        builder = @Builder(disableBuilder = true))
public abstract class RegistrationMapper {
    @Autowired
    private ExperienceMapper experienceMapper;

    @Mapping(source = "personal", target = "user")
    public abstract Registration toEntity(RegistrationRequestDto dto);

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
