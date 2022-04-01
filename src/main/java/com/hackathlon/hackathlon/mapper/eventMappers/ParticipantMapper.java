package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.dto.responses.eventDtos.ParticipantResponseDto;
import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.mapper.registrationMappers.RegistrationMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        uses = {
                RegistrationMapper.class,
        },
        builder = @Builder(disableBuilder = true)
)
public interface ParticipantMapper {
    @Mapping(source="UUID", target="registration")
    @Mapping(source="registration.user.basicInfo.email", target="email")
    ParticipantResponseDto toDto(Registration registration);
//    default ParticipantResponseDto map(Registration registrations) {
//        return value.toString();
//    }
}
