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
    public Week create(Long eventId,
                       Long userId,
                       Integer weekNo,
                       WeekReportRequestDto dto) throws NoSuchElementException, IllegalArgumentException {

        Event event = getEventIfExists(eventId);
//        TODO: assert user is part of event?
        User user = getUserIfExists(userId);
        validateWeekNo(weekNo, event);

        Week week = weekMapper.toEntity(dto);
        week.setUser(user);
        week.setWeekNumber(weekNo);
        Week savedWeek = weekRepository.save(week);
        return savedWeek;
    }

    private void validateWeekNo(Integer weekNo, Event event) throws IllegalArgumentException {
        if (weekNo > event.getWeeks()) {
            throw new IllegalArgumentException();
        }
    }

    private Event getEventIfExists(Long eventId) throws NoSuchElementException {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new NoSuchElementException();
        }
        return event.get();
    }

    private User getUserIfExists(Long userId) throws NoSuchElementException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NoSuchElementException();
        }
        return user.get();
    }
}
