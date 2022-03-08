package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.entity.user.*;
import org.mapstruct.*;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface WeekMapper {
    Week toEntity(WeekReportRequestDto dto);
}
