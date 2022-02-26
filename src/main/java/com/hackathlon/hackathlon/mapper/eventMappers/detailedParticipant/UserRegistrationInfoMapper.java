package com.hackathlon.hackathlon.mapper.eventMappers.detailedParticipant;

import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface UserRegistrationInfoMapper {
    @Mapping(source="registration.participation", target="participation")
    @Mapping(source="registration.kickoff", target="kickoff")
    @Mapping(source="registration.user.fluff.TShirt", target="tshirt")
    UserRegistrationInfoDto toDto(Registration registration);
}
