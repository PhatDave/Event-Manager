package com.hackathlon.hackathlon.mapper;

import com.hackathlon.hackathlon.dto.requests.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(builder=@Builder(disableBuilder=true))
public interface TeamMapper {
    Event toEntity(EventRequestDto dto);

}