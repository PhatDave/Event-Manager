package com.hackathlon.mapper.eventMappers;

import com.hackathlon.dto.requests.eventDtos.WeekReportRequestDto;
import com.hackathlon.dto.responses.eventDtos.detailedParticipant.WeekDto;
import com.hackathlon.dto.responses.eventDtos.detailedParticipant.WeekProgressDto;
import com.hackathlon.entity.user.User;
import com.hackathlon.entity.user.Week;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
