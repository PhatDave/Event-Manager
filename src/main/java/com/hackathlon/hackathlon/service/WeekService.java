package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.eventDtos.WeekReportRequestDto;
import com.hackathlon.hackathlon.entity.user.Week;

public interface WeekService {
    Week create(Long eventId, Long userId, Integer weekNo, WeekReportRequestDto dto);
}
