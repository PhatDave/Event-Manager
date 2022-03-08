package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(
        uses = {
                TeamMapper.class,
        },
        builder = @Builder(disableBuilder = true)
)
public interface EventMapper {
    //    @Mapping(source="registrationsNotBefore", target="registerNotBefore")
    Event toEntity(EventRequestDto dto);

    EventRequestDto toDto(Event event);

    @AfterMapping
    default void mapEventIdInUser(@MappingTarget Event event) {
        event.getTeams().forEach(team -> team.setEvent(event));
    }
}
