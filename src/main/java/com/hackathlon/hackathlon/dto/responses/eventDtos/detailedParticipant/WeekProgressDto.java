package com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant;

import com.hackathlon.hackathlon.entity.enums.*;
import lombok.*;

import java.util.*;

//TODO: separate to files
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeekProgressDto {
    List<WeekDto> progress;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class WeekDto {
    Integer weekNo;
    List<WeekStatusDto> flow;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class WeekStatusDto {
    WeekStatus status;
    String comment;
}
