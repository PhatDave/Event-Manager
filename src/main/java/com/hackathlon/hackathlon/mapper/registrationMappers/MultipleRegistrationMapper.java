package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

import java.util.*;
@Mapper(
        uses = {
                RegistrationMapper.class,
        },
        builder = @Builder(disableBuilder=true)
)
public interface MultipleRegistrationMapper {
    @Mapping(source = "registration", target = "content")
    default MultipleRegistrationResponseDto toDto(List<RegistrationResponseDto> registrations) {
        MultipleRegistrationResponseDto dto = new MultipleRegistrationResponseDto();
        dto.setContent(registrations);
        return dto;
    }
}
