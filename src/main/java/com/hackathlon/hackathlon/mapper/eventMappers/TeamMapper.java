package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.dto.requests.eventDtos.TeamRequestDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.TeamResponseDto;
import com.hackathlon.hackathlon.entity.Team;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.stream.Collectors;

@Mapper(
        uses = {
                MentorMapper.class,
        },
        builder = @Builder(disableBuilder = true)
)
public interface TeamMapper {
    Team toEntity(TeamRequestDto dto);

    TeamResponseDto toDto(Team team);

    @AfterMapping
    default void mapUsersToMembers(Team team, @MappingTarget TeamResponseDto dto) {
        dto.setMembers(team.getUsers().stream().map(user -> user.getBasicInfo().getEmail()).collect(Collectors.toList()));
    }

    @AfterMapping
    default void mapTeamIdInMentor(@MappingTarget Team team) {
        if (CollectionUtils.isNotEmpty(team.getMentors())) {
            team.getMentors().forEach(mentor -> mentor.setTeam(team));
        }
    }
}
