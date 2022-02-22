package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

import java.util.stream.*;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface TeamResponseMapper {
    default TeamResponseDto toDto(Team team) {
        TeamResponseDto dto = new TeamResponseDto();
        dto.setName(team.getName());
        dto.setMembers(team.getUsers().stream().map(user -> user.getBasicInfo().getEmail()).collect(Collectors.toList()));
        return dto;
    }
}
