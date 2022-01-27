package com.hackathlon.hackathlon.mapper;

import com.hackathlon.hackathlon.dto.requests.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(
        uses = {
                TeamMapper.class,
        },
        builder = @Builder(disableBuilder=true)
)
public interface EventMapper {
//    @Mapping(source="registrationsNotBefore", target="registerNotBefore")
    Event toEntity(EventRequestDto dto);
    EventRequestDto toDto(Event event);
}