package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.eventDtos.EventRequestDto;
import com.hackathlon.hackathlon.entity.Event;
import com.hackathlon.hackathlon.mapper.eventMappers.EventMapper;
import com.hackathlon.hackathlon.repository.EventRepository;
import com.hackathlon.hackathlon.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
//        below code is moved to mappers
//        event.getTeams().forEach(team -> team.setEvent(event));
//        List<Team> teams = event.getTeams();
//        for(Team team : teams){
//            if(CollectionUtils.isNotEmpty(team.getMentors())){
//                team.getMentors().forEach(mentor -> mentor.setTeam(team));
//            }
//        }
        Event savedEvent = eventRepository.save(event);
        return savedEvent;
    }
}
