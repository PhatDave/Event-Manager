package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    private ResponseEntity<String> create(@RequestBody EventRequestDto eventRequestDto) {
        System.out.println(eventRequestDto);
        return ResponseEntity.ok(HttpStatus.CREATED.toString());
    }
}
