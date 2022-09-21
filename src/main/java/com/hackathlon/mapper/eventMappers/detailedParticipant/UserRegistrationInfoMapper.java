package com.hackathlon.mapper.eventMappers.detailedParticipant;

import com.hackathlon.dto.responses.eventDtos.detailedParticipant.UserRegistrationInfoDto;
import com.hackathlon.entity.Registration;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface UserRegistrationInfoMapper {
    @Mapping(source="registration.participation", target="participation")
    @Mapping(source="registration.kickoff", target="kickoff")
    @Mapping(source="registration.user.fluff.TShirt", target="tshirt")
    UserRegistrationInfoDto toDto(Registration registration);
}
