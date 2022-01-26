package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(builder = @Builder(disableBuilder=true))
public interface RegistrationMapper {
    Registration toEntity(RegistrationRequestDto dto);
}
