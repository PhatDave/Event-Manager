package com.hackathlon.hackathlon.mapper.eventMappers.detailedParticipant;

import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

import java.util.*;

@Mapper(
        uses = {
                DetailedRegistrationMapper.class,
                UserRegistrationInfoMapper.class,
        },
        builder = @Builder(disableBuilder = true)
)
public interface DetailedParticipantDtoMapper {
    List<DetailedParticipantDto> toDto(List<Registration> registrations);

//    @Mapping(source="registration", target=".")
    DetailedParticipantDto toDto(Registration registration);
}
