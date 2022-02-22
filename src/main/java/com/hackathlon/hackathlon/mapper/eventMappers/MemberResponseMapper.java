package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import com.hackathlon.hackathlon.entity.user.*;
import org.mapstruct.*;

import java.util.*;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface MemberResponseMapper {
    List<MemberResponseDto> toDto(List<User> users);
    default MemberResponseDto toDto(User user) {
        MemberResponseDto dto = new MemberResponseDto();
        dto.setEmail(user.getBasicInfo().getEmail());
        return dto;
    }
}
