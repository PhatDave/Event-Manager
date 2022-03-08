package com.hackathlon.hackathlon.mapper.eventMappers.detailedParticipant;

import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.*;
import com.hackathlon.hackathlon.entity.user.*;
import org.mapstruct.*;

@Mapper(
        uses = {
        },
        builder = @Builder(disableBuilder = true)
)
public interface WeekProgressMapper {
    WeekProgressDto toDto(User user);
}
