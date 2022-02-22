package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.*;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public abstract class TeamResponseMapper {
    @Autowired
    private MemberResponseMapper memberResponseMapper;

    public TeamResponseDto toDto(PartitionedTeams.PTeam team) {
        TeamResponseDto dto = new TeamResponseDto();
        dto.setName("Pero");
//        TODO: Name wtf??
        dto.setMembers(memberResponseMapper.toDto(team.getMembers()));
        return dto;
    }
}
