package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.dto.requests.eventDtos.MentorRequestDto;
import com.hackathlon.hackathlon.entity.Mentor;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
        builder=@Builder(disableBuilder=true)
)
public interface MentorMapper {
    Mentor toEntity(MentorRequestDto dto);
}