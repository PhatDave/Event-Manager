package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event/{eventID}/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    private ResponseEntity<?> create(@PathVariable Long eventID, @RequestBody RegistrationRequestDto registrationRequestDto) {
        try {
            System.out.println(registrationRequestDto);
            registrationService.create(eventID, registrationRequestDto);
//            return ResponseEntity.created(URI.create("/event/" + event.getID())).body("");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
