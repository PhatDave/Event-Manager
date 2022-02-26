package com.hackathlon.hackathlon.mapper.eventMappers.detailedParticipant;

import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.mapper.registrationMappers.*;
import org.mapstruct.*;

import java.util.*;

@Mapper(
        uses = {
                DetailedRegistrationMapper.class,
                UserRegistrationInfoMapper.class,
        },
        builder = @Builder(disableBuilder = true)
)
public interface DetailedParticipantDtoMapper {
//                TODO: ????????????????????????
//        if DetailedParticipantDto has only RegistrationResponseDto then
//        detailedParticipantDto.setRegistration( detailedRegistrationMapper.toDto( registration ) );

//        if DetailedParticipantDto has RegistrationResponseDto and List<CommentResponseDto> then
//        detailedParticipantDto.setComments( commentListToCommentResponseDtoList( registration.getComments() ) );
//        but no
//        detailedParticipantDto.setRegistration( detailedRegistrationMapper.toDto( registration ) );
    List<DetailedParticipantDto> toDto(List<Registration> registrations);

    @Mapping(source="registration", target=".")
    DetailedParticipantDto toDto(Registration registration);
}
