package com.hackathlon.hackathlon.mapper.eventMappers.detailedParticipant;

import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.WeekProgressDto;
import com.hackathlon.hackathlon.entity.user.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
        uses = {
        },
        builder = @Builder(disableBuilder = true)
)
public interface WeekProgressMapper {
    WeekProgressDto toDto(User user);
}
