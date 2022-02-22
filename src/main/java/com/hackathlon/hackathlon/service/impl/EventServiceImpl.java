package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.eventDtos.EventRequestDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.enums.*;
import com.hackathlon.hackathlon.mapper.eventMappers.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;
    private final EventMapper eventMapper;
    private final ParticipantMapper participantMapper;

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

    @Override
    public ParticipantsResponseDto inviteParticipants(Long eventId) throws NoSuchElementException, IllegalArgumentException {
        var event = eventRepository.findById(eventId);
        Event eventObj = getEventIfExists(event);
        checkEventStatus(eventObj);
        setEventStatus(eventObj);

        var registrations = registrationRepository.findAllByEventID(eventId);
        sortRegistrationsByScore(registrations);
        registrations = getMaxRegistrations(eventObj, registrations);
        setRegistrationsStatus(registrations);

        List<ParticipantResponseDto> participants = registrations.stream().map(participantMapper::toDto).collect(Collectors.toList());
        ParticipantsResponseDto participantsDto = new ParticipantsResponseDto(participants);
        return participantsDto;
    }

    private void setEventStatus(Event eventObj) {
        eventObj.setStatus(EventStatusEnum.INVITED);
        eventRepository.save(eventObj);
    }

    private void setRegistrationsStatus(List<Registration> registrations) {
        for (Registration reg : registrations) {
            reg.setStatus(RegistrationStatusEnum.INVITED);
            registrationRepository.save(reg);
        }
    }

    private void checkEventStatus(Event eventObj) throws IllegalArgumentException {
        if (eventObj.getStatus() == EventStatusEnum.INVITED) {
//            TODO: good idea?
            throw new IllegalArgumentException();
        }
    }

    private Event getEventIfExists(Optional<Event> event) throws NoSuchElementException {
        if (event.isEmpty()) {
            throw new NoSuchElementException();
        }
        Event eventObj = event.get();
        return eventObj;
    }

    private void sortRegistrationsByScore(List<Registration> registrations) {
        registrations.sort((Registration regA, Registration regB) -> regA.getScore().compareTo(regB.getScore()));
        Collections.reverse(registrations);
    }

    private List<Registration> getMaxRegistrations(Event eventObj, List<Registration> registrations) {
        registrations = registrations.subList(0, Math.min(eventObj.getMaxParticipants(), registrations.size()));
        return registrations;
    }
}
