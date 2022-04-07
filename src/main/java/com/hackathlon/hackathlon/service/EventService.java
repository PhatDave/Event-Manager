package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.eventDtos.EventRequestDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.ParticipantsResponseDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.TeamsResponseDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.DetailedParticipantDto;
import com.hackathlon.hackathlon.entity.Event;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    List<Event> getAll();
    Event getById(Long id);
    Event create(EventRequestDto dto);
    ParticipantsResponseDto inviteParticipants(Long id);
    TeamsResponseDto getTeams(Long id);
    TeamsResponseDto teamUp(Long eventId);
    List<DetailedParticipantDto> getDetailedParticipants(Long eventId, Pageable pageable);
    void updateEvents();
}
