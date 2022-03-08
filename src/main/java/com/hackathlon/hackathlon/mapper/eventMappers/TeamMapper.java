package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.apache.commons.collections4.*;
import org.mapstruct.*;

import java.util.stream.*;

@Mapper(
        uses = {
                MentorMapper.class,
        },
        builder = @Builder(disableBuilder = true)
)
public interface TeamMapper {
    Team toEntity(TeamRequestDto dto);

//    @Mapping(source = "users", target = "members")
    TeamResponseDto toDto(Team team);
//    Can't map property "List<User> users" to "List<String> members". Consider to declare/implement a mapping method: "List<String> map(List<User> value)".

//    {
//        TeamResponseDto dto = new TeamResponseDto();
//        dto.setName(team.getName());
//        dto.setMembers(team.getUsers().stream().map(user -> user.getBasicInfo().getEmail()).collect(Collectors.toList()));
//        return dto;
//    }

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
