package com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant;

import com.hackathlon.hackathlon.entity.enums.WeekStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeekStatusDto {
    WeekStatus status;
    String comment;
}