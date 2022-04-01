package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.eventDtos.EventRequestDto;
import com.hackathlon.hackathlon.dto.requests.eventDtos.WeekReportRequestDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.ParticipantsResponseDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.TeamsResponseDto;
import com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant.DetailedParticipantsDto;
import com.hackathlon.hackathlon.entity.Event;
import com.hackathlon.hackathlon.service.EventService;
import com.hackathlon.hackathlon.service.WeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
