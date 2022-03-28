package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.*;
import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.domain.*;

import java.util.*;

public interface EventService {
    List<Event> getAll();
    Event getById(Long id);
    Event create(EventRequestDto dto);
    ParticipantsResponseDto inviteParticipants(Long id);
    TeamsResponseDto teamUp(Long eventId);
    List<DetailedParticipantDto> getDetailedParticipants(Long eventId, Pageable pageable);
    void updateEvents();
}
