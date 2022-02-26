package com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant;

import com.hackathlon.hackathlon.entity.enums.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeekStatusDto {
    WeekStatus status;
    String comment;
}