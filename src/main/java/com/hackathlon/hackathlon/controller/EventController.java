package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.dao.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    private ResponseEntity<?> create(@RequestBody EventRequestDto eventRequestDto) {
        try {
            System.out.println(eventRequestDto);
            eventService.create(eventRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
