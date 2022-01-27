package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(
        builder=@Builder(disableBuilder=true)
)
public interface MentorMapper {
    Mentor toEntity(MentorRequestDto dto);
}