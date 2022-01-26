package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.dao.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event/{eventID}/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    @PostMapping
    private ResponseEntity<?> create(@RequestBody RegistrationRequestDto registrationRequestDto) {
        try {
            System.out.println(registrationRequestDto);
//            Event event = eventService.create(eventRequestDto);
//            return ResponseEntity.created(URI.create("/event/" + event.getID())).body("");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
