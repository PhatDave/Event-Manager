package com.hackathlon.mapper.eventMappers.detailedParticipant;

import com.hackathlon.dto.responses.eventDtos.detailedParticipant.DetailedParticipantDto;
import com.hackathlon.entity.Registration;
import com.hackathlon.mapper.eventMappers.WeekMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

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
