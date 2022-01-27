package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.mapper.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
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
        event.getTeams().forEach(team -> team.setEvent(event));
        List<Team> teams = event.getTeams();
        for(Team team : teams){
            if(CollectionUtils.isNotEmpty(team.getMentors())){
                team.getMentors().forEach(mentor -> mentor.setTeam(team));
            }
        }
        Event savedEvent = eventRepository.save(event);
        return savedEvent;
    }
}
