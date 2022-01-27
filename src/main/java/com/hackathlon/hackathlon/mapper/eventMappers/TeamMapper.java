package com.hackathlon.hackathlon.mapper.eventMappers;

import com.hackathlon.hackathlon.dto.requests.eventDtos.TeamRequestDto;
import com.hackathlon.hackathlon.entity.Team;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        uses = {
                MentorMapper.class,
        },
        builder = @Builder(disableBuilder = true)
)
public interface TeamMapper {
    Team toEntity(TeamRequestDto dto);

    @AfterMapping
    default void mapTeamIdInMentor(@MappingTarget Team team) {
        if (CollectionUtils.isNotEmpty(team.getMentors())) {
            team.getMentors().forEach(mentor -> mentor.setTeam(team));
        }
    }
}
