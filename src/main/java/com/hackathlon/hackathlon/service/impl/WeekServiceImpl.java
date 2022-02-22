package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.user.*;
import com.hackathlon.hackathlon.mapper.eventMappers.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final WeekRepository weekRepository;

    private final WeekMapper weekMapper;

    @Override
    public Week create(Long eventId, Long userId, Integer weekNo, WeekReportRequestDto dto) throws NoSuchElementException {
        Event event = getEventIfExists(eventId);
        User user = getUserIfExists(userId);

        Week week = weekMapper.toEntity(dto);
        week.setUser();
        Week savedWeek = weekRepository.save(week);
        return savedWeek;
    }

    private Event getEventIfExists(Long eventId) throws NoSuchElementException {
        var event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new NoSuchElementException();
        }
        return event.get();
    }

    private User getUserIfExists(Long userId) throws NoSuchElementException {
        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NoSuchElementException();
        }
        return user.get();
    }
}
