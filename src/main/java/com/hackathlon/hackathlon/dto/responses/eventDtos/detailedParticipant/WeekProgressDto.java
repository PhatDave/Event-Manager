package com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeekProgressDto {
    List<WeekDto> progress;
}