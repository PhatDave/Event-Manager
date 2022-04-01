package com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeekProgressDto {
    List<WeekDto> progress;
}