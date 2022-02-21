package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.eventDtos.*;
import com.hackathlon.hackathlon.dto.responses.eventDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.apache.coyote.*;
import org.springframework.dao.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;
import java.util.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    private ResponseEntity<?> create(@RequestBody EventRequestDto eventRequestDto) {
        try {
            Event event = eventService.create(eventRequestDto);
            return ResponseEntity.created(URI.create("/event/" + event.getID())).body("");
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{eventId}/invite")
    private ResponseEntity<?> invite(@PathVariable Long eventId) {
        ParticipantsResponseDto participants = eventService.inviteParticipants(eventId);
        return ResponseEntity.ok(participants);
    }
}
