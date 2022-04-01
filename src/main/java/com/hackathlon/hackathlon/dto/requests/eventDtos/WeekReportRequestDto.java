package com.hackathlon.hackathlon.dto.requests.eventDtos;

import com.hackathlon.hackathlon.entity.enums.WeekStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeekReportRequestDto {
    private WeekStatus status;
    private String comment;
}
