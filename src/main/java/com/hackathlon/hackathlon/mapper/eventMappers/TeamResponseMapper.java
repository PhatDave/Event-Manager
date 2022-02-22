package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import org.mapstruct.*;

import java.util.stream.*;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface TeamResponseMapper {
    default TeamResponseDto toDto(PartitionedTeams.PTeam team) {
        TeamResponseDto dto = new TeamResponseDto();
        dto.setName("Pero");
//        TODO: Name wtf??
        dto.setMembers(team.getMembers().stream().map(user -> user.getBasicInfo().getEmail()).collect(Collectors.toList()));
        return dto;
    }
}
