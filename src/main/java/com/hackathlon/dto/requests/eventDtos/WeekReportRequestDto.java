package com.hackathlon.dto.requests.eventDtos;

import com.hackathlon.entity.enums.WeekStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeekReportRequestDto {
    private WeekStatus status;
    private String comment;
}
