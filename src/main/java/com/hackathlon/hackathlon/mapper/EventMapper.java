package com.hackathlon.hackathlon.mapper;

import com.hackathlon.hackathlon.dto.requests.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(
        uses = {
                TeamMapper.class,
        },
        builder=@Builder(disableBuilder=true)
)
public interface EventMapper {
    Event toEntity(EventRequestDto dto);
}