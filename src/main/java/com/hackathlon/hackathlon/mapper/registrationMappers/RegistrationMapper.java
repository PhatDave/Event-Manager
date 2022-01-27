package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.ExperienceRequestDto;
import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.entity.User.Experience;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        mapExperience(dto, registration);

        if (!dto.getMotivation().isBlank()) {
            registration.getUser().getFluff().setMotivation(dto.getMotivation());
        }
        if (!dto.getPreferredOS().isBlank()) {
            registration.getUser().getFluff().setPreferredOS(dto.getPreferredOS());
        }

        // map registrationId inside user
        registration.getUser().setRegistration(registration);
    }

    private void mapExperience(RegistrationRequestDto dto, Registration registration) {
        // BLACK MAGIC
        List<Experience> experiences = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dto.getExperiences())) {
            for (ExperienceRequestDto experienceRequestDto : dto.getExperiences()) {
                experiences.add(experienceMapper.toEntity(experienceRequestDto));
            }
            registration.getUser().getExperiences().clear();
            registration.getUser().getExperiences().addAll(experiences);

            // mapping userId inside experience
            for (Experience experience : registration.getUser().getExperiences()) {
                experience.setUser(registration.getUser());
            }
        }
    }
}
