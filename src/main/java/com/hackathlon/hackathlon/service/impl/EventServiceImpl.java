package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.mapper.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public List<Event> getAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Optional<Event> getById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public Event create(EventRequestDto dto) {
        Event event = eventMapper.toEntity(dto);
        Event savedEvent = eventRepository.save(event);
        return savedEvent;
    }
}
