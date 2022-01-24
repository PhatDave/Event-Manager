package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public List<Event> getAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Optional<Event> getById(Long id) {
        return this.eventRepository.findById(id);
    }
}
