package com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeekDto {
    Integer week;
    List<WeekStatusDto> flow;
}