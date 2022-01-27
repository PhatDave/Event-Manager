package com.hackathlon.hackathlon.mapper;

import com.hackathlon.hackathlon.dto.requests.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(
        uses = {
                MentorMapper.class,
        },
        builder=@Builder(disableBuilder=true)
)
public interface TeamMapper {
    Team toEntity(TeamRequestDto dto);
}