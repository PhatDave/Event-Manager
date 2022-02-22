package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.*;
import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.user.*;
import com.hackathlon.hackathlon.enums.*;
import com.hackathlon.hackathlon.mapper.eventMappers.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    private final EventMapper eventMapper;
    private final ParticipantMapper participantMapper;
    private final TeamResponseMapper teamResponseMapper;

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

    @Override
    public TeamsResponseDto teamUp(Long eventId) {
        Event event = getEventIfExists(eventId);
        List<Team> teams = event.getTeams();
        var registrations = event.getRegistrations();

        var acceptedRegistrations = filterAcceptedRegistrations(registrations);
        var users = getAllUsersFromRegistrations(acceptedRegistrations);

        PartitionedTeams partitionedTeams = new PartitionedTeams(3, users);
        TeamsResponseDto teamsDto = getDtoFromPTeam(partitionedTeams);

        return teamsDto;
    }

    private TeamsResponseDto getDtoFromPTeam(PartitionedTeams partitionedTeams) {
        List<TeamResponseDto> teamDtos = partitionedTeams.getTeams().stream().map(teamResponseMapper::toDto).collect(Collectors.toList());
        TeamsResponseDto teamsDto = new TeamsResponseDto(teamDtos);
        return teamsDto;
    }

    private List<User> getAllUsersFromRegistrations(List<Registration> acceptedRegistrations) {
        ArrayList<User> users = new ArrayList<>();
        for (Registration reg : acceptedRegistrations) {
            users.add(reg.getUser());
        }
        return users;
    }

    private List<Registration> filterAcceptedRegistrations(List<Registration> registrations) {
        ArrayList<Registration> filteredRegistrations = new ArrayList<Registration>();
        for (Registration reg : registrations) {
            if (reg.getStatus() == RegistrationStatusEnum.ACCEPTED) {
                filteredRegistrations.add(reg);
            }
        }
        return filteredRegistrations;
    }

    private Event getEventIfExists(Long eventId) {
        var event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new NoSuchElementException();
        }
        return event.get();
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
