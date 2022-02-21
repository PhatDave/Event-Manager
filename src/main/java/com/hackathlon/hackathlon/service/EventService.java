package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import com.hackathlon.hackathlon.entity.*;

import java.util.*;

public interface EventService {
    List<Event> getAll();
    Optional<Event> getById(Long id);
    Event create(EventRequestDto dto);
    ParticipantsResponseDto inviteParticipants(Long id);
}
