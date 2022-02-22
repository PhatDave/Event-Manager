package com.hackathlon.hackathlon.dto.requests.eventDtos;

import com.hackathlon.hackathlon.entity.enums.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeekReportRequestDto {
    private WeekStatus status;
    private String comment;
}
