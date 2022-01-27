package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.dao.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    private ResponseEntity<?> create(@RequestBody EventRequestDto eventRequestDto) {
        try {
            System.out.println(eventRequestDto);
            Event event = eventService.create(eventRequestDto);
            return ResponseEntity.created(URI.create("/event/" + event.getID())).body("");
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
