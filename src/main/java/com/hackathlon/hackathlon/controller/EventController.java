package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.dao.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;
import java.util.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final WeekService weekService;

    @PostMapping
    private ResponseEntity<?> create(@RequestBody EventRequestDto eventRequestDto) {
        Event event = eventService.create(eventRequestDto);
        return ResponseEntity.created(URI.create("/event/" + event.getID())).body("");
    }

    @PutMapping("/{eventId}/invite")
    private ResponseEntity<?> invite(@PathVariable Long eventId) {
        ParticipantsResponseDto participants = eventService.inviteParticipants(eventId);
        return ResponseEntity.ok(participants);
    }

    @PutMapping("/{eventId}/teamUp")
    private ResponseEntity<?> teamUp(@PathVariable Long eventId) {
        TeamsResponseDto dto = eventService.teamUp(eventId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{eventId}/participants/{userId}/week/{weekNo}")
    private ResponseEntity<?> addWeekProgress(@PathVariable Long eventId,
                                              @PathVariable Long userId,
                                              @PathVariable Integer weekNo,
                                              @RequestBody WeekReportRequestDto dto) {
        weekService.create(eventId, userId, weekNo, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{eventId}/participants")
    private ResponseEntity<DetailedParticipantsDto> getParticipants(@PathVariable Long eventId, Pageable pageable) {
        var detailedParticipants = eventService.getDetailedParticipants(eventId, pageable);
        var detailedParticipantsDto = new DetailedParticipantsDto(detailedParticipants);
        return ResponseEntity.ok(detailedParticipantsDto);
    }
}
