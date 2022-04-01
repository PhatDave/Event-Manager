package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.eventDtos.WeekReportRequestDto;
import com.hackathlon.hackathlon.entity.Event;
import com.hackathlon.hackathlon.entity.user.User;
import com.hackathlon.hackathlon.entity.user.Week;
import com.hackathlon.hackathlon.mapper.eventMappers.WeekMapper;
import com.hackathlon.hackathlon.repository.EventRepository;
import com.hackathlon.hackathlon.repository.UserRepository;
import com.hackathlon.hackathlon.repository.WeekRepository;
import com.hackathlon.hackathlon.service.WeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final WeekRepository weekRepository;

    private final WeekMapper weekMapper;

    @Override
    public Week create(Long eventId,
                       Long userId,
                       Integer weekNo,
                       WeekReportRequestDto dto) {

        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoSuchElementException("Event with id " + eventId + " not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
        validateWeekNo(weekNo, event);

        Week week = weekMapper.toEntity(dto);
        week.setUser(user);
        week.setWeekNumber(weekNo);
        Week savedWeek = weekRepository.save(week);
        return savedWeek;
    }

    private void validateWeekNo(Integer weekNo, Event event) {
        if (weekNo > event.getWeeks()) {
            throw new IllegalArgumentException("Week number " + weekNo + " is greater than event's weeks " + event.getWeeks());
        }
    }
}
