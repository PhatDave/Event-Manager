package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;
import java.util.*;

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

    @DeleteMapping("/{registrationUUID}")
    private ResponseEntity<?> delete(@PathVariable Long eventID, @PathVariable String registrationUUID) {
        var event = eventService.getById(eventID);
        var registration = registrationService.getByUUID(registrationUUID);
        if (event.isEmpty() || registration.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.registrationService.delete(registration.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
