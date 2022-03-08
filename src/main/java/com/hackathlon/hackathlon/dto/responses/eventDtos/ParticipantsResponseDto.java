package com.hackathlon.hackathlon.dto.responses.eventDtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantsResponseDto {
    List<ParticipantResponseDto> participants;
}