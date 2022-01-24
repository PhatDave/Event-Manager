package com.hackathlon.hackathlon.mapper;

import com.hackathlon.hackathlon.dto.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(builder=@Builder(disableBuilder=true))
public interface EventMapper {
    Event toEntity(EventRequestDto dto);
}