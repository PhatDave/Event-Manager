package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.*;
import com.hackathlon.hackathlon.entity.user.*;
import org.mapstruct.*;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface WeekMapper {
    Week toEntity(WeekReportRequestDto dto);

    @Mapping(source="user.weeks", target="progress")
    WeekProgressDto toDto(User user);

//    TODO: group weeks by number?
//    TODO: how do aggregate weeks?
    @Mapping(source="week.weekNumber", target="week")
    WeekDto toDto(Week week);
//    WeekStatusDto toDto(Week week);
}
