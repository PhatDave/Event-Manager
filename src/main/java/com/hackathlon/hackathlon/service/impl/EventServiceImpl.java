package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.PartitionedTeams;
import com.hackathlon.hackathlon.dto.requests.eventDtos.EventRequestDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.ParticipantResponseDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.ParticipantsResponseDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.TeamResponseDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.TeamsResponseDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.DetailedParticipantDto;
import com.hackathlon.hackathlon.entity.Event;
import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.entity.Team;
import com.hackathlon.hackathlon.entity.user.User;
import com.hackathlon.hackathlon.enums.EventStatusEnum;
import com.hackathlon.hackathlon.enums.RegistrationStatusEnum;
import com.hackathlon.hackathlon.mapper.eventMappers.EventMapper;
import com.hackathlon.hackathlon.mapper.eventMappers.ParticipantMapper;
import com.hackathlon.hackathlon.mapper.eventMappers.TeamMapper;
import com.hackathlon.hackathlon.mapper.eventMappers.detailedParticipant.DetailedParticipantDtoMapper;
import com.hackathlon.hackathlon.repository.EventRepository;
import com.hackathlon.hackathlon.repository.RegistrationRepository;
import com.hackathlon.hackathlon.repository.TeamRepository;
import com.hackathlon.hackathlon.service.EmailSender;
import com.hackathlon.hackathlon.service.EventService;
import com.hackathlon.hackathlon.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;
    private final TeamRepository teamRepository;
    private final EmailSender emailSender;

    private final TeamService teamService;

    private final EventMapper eventMapper;
    private final ParticipantMapper participantMapper;
    private final TeamMapper teamMapper;
    private final DetailedParticipantDtoMapper detailedParticipantDtoMapper;

    @Override
    public List<Event> getAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event getById(Long id) {
        return this.eventRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Event with id " + id + " not found"));
    }

    @Override
    public Event create(EventRequestDto dto) {
        throwIfExists(dto);
        Event event = eventMapper.toEntity(dto);
        event.setStatus(EventStatusEnum.NOT_INVITED);
        Event savedEvent = eventRepository.save(event);
        return savedEvent;
    }

    @Override
    public ParticipantsResponseDto inviteParticipants(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoSuchElementException("Event with id " + eventId + " not found"));
        checkEventStatus(event);
        setEventStatus(event);

        List<Registration> registrations = registrationRepository.findAllByEventID(eventId);
        sortRegistrationsByScore(registrations);
        registrations = getMaxRegistrations(event, registrations);
        setRegistrationsStatus(registrations);

        List<ParticipantResponseDto> participants = registrations.stream().map(participantMapper::toDto).collect(Collectors.toList());
        ParticipantsResponseDto participantsDto = new ParticipantsResponseDto(participants);
        return participantsDto;
    }

    @Override
    public TeamsResponseDto getTeams(Long id) {
        Event event = getById(id);
        List<Team> teams = teamService.getByEventId(id);
        return new TeamsResponseDto(teams.stream().map(teamMapper::toDto).collect(Collectors.toList()));
    }

    @Override
    public TeamsResponseDto teamUp(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoSuchElementException("Event with id " + eventId + " not found"));
        List<Team> teams = event.getTeams();
        List<Registration> registrations = event.getRegistrations();

        List<Registration> acceptedRegistrations = filterAcceptedRegistrations(registrations);
        List<User> users = getAllUsersFromRegistrations(acceptedRegistrations);

        validateUsersHaveNoTeam(users);

        PartitionedTeams partitionedTeams = new PartitionedTeams(teams, users, teamRepository);
        TeamsResponseDto teamsDto = getDtoFromPTeam(partitionedTeams);

        return teamsDto;
    }

    @Override
    public List<DetailedParticipantDto> getDetailedParticipants(Long eventId, Pageable pageable) {
        Page<Registration> registrations = registrationRepository.findAllByEventID(eventId, pageable);
        return detailedParticipantDtoMapper.toDto(registrations);
    }

    @Override
    public void updateEvents() {
        var events = eventRepository.findAll();
        var timeNow = new Date();
        for (Event event : events) {
            if (event.getConfirmationNotAfter().after(timeNow) && event.getStatus() == EventStatusEnum.NOT_INVITED) {
                this.inviteParticipants(event.getID());
            }
        }
    }

    private void throwIfExists(EventRequestDto dto) {
        if (this.eventRepository.existsByName(dto.getName())) {
            throw new DataIntegrityViolationException("Event with name " + dto.getName() + " already exists");
        }
    }

    private void validateUsersHaveNoTeam(List<User> users) {
        for (User user : users) {
            if (user.getTeam() != null) {
                throw new IllegalStateException("User " + user.getBasicInfo().getFirstName() + " " + user.getBasicInfo().getLastName() + " already has a team");
            }
        }
    }

    private TeamsResponseDto getDtoFromPTeam(PartitionedTeams partitionedTeams) {
        List<TeamResponseDto> teamDtos = partitionedTeams.getTeams().stream().map(teamMapper::toDto).collect(Collectors.toList());
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

    private void setEventStatus(Event eventObj) {
        eventObj.setStatus(EventStatusEnum.INVITED);
        eventRepository.save(eventObj);
    }

    private void setRegistrationsStatus(List<Registration> registrations) {
        for (Registration reg : registrations) {
            emailSender.sendEmail(reg.getUser().getBasicInfo().getEmail(), "Event invitation", "You have been invited to " + reg.getEvent().getName());
            reg.setStatus(RegistrationStatusEnum.INVITED);
            registrationRepository.save(reg);
        }
    }

    private void checkEventStatus(Event event) {
        if (event.getStatus() == EventStatusEnum.INVITED) {
            throw new IllegalStateException("Participants have already been invited to event " + event.getName());
        }
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
