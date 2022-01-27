package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.*;
import java.util.*;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@RestController
@RequestMapping("/event/{eventID}/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final EventService eventService;

    @PostMapping
    private ResponseEntity<?> create(@PathVariable Long eventID, @RequestBody RegistrationRequestDto registrationRequestDto) {
        Optional<Event> event = eventService.getById(eventID);
        if (event.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Date today = Calendar.getInstance().getTime();
            if (event.get().getRegistrationsNotAfter().after(today)) {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }
        Registration registration = registrationService.create(eventID, registrationRequestDto);
        return ResponseEntity.created(URI.create("/event/" + registration.getEvent().getID() + "/registrations/" + registration.getUUID())).body("");
    }
}
