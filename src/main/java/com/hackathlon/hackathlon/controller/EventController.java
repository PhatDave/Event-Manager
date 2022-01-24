package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    private ResponseEntity<EventResponseDto> create(@RequestBody EventRequestDto eventRequestDto) {
        System.out.println(eventRequestDto);
//        return ResponseEntity.ok(eventService.create(eventRequestDto));
    }
}
