package com.hackathlon.hackathlon.mapper.eventMappers.detailedParticipant;

import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.mapper.eventMappers.*;
import org.mapstruct.*;
import org.springframework.data.domain.*;

import java.util.*;

@Mapper(
        uses = {
                DetailedRegistrationMapper.class,
                UserRegistrationInfoMapper.class,
                WeekMapper.class,
        },
        builder = @Builder(disableBuilder = true)
)
public interface DetailedParticipantDtoMapper {
    List<DetailedParticipantDto> toDto(Page<Registration> registrations);

    @Mapping(source=".", target="registration")
    @Mapping(source=".", target="additionalInfo")
    @Mapping(source="registration.user", target="weekProgress")
    DetailedParticipantDto toDto(Registration registration);
}
