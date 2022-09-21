package com.hackathlon.service;

import com.hackathlon.dto.requests.eventDtos.WeekReportRequestDto;
import com.hackathlon.entity.user.Week;

public interface WeekService {
    Week create(Long eventId, Long userId, Integer weekNo, WeekReportRequestDto dto);
}
