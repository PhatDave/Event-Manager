package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.data.domain.*;
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
    private final CommentService commentService;

    @PostMapping
    private ResponseEntity<?> createRegistration(@PathVariable Long eventID, @RequestBody RegistrationRequestDto registrationRequestDto) {
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
    private ResponseEntity<?> deleteRegistrationByUUID(@PathVariable Long eventID, @PathVariable String registrationUUID) {
        var event = eventService.getById(eventID);
        var registration = registrationService.getByUUID(registrationUUID);
        if (event.isEmpty() || registration.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.registrationService.delete(registration.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{registrationUUID}")
    private ResponseEntity<RegistrationResponseDto> getRegistrationByUUID(@PathVariable Long eventID, @PathVariable String registrationUUID) {
        var dto = registrationService.getRegistrationDtoByUUID(registrationUUID);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("")
    private ResponseEntity<Page<RegistrationResponseDto>> getRegistrations(@PathVariable Long eventID, Pageable pageable) {
        return ResponseEntity.ok(registrationService.getAllbyEventId(eventID, pageable));
    }

    @PatchMapping("/{registrationUUID}")
    private ResponseEntity<Void> acceptInvitation(@PathVariable Long eventID, @PathVariable String registrationUUID, @RequestBody InvitationRequestDto invitationRequestDto) {
        try {
            // TODO isprobaj ovako
            registrationService.handleInvite(registrationUUID, invitationRequestDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{registrationUUID}/score")
    private ResponseEntity<?> manuallyScoreRegistrationByUUID(@PathVariable Long eventID, @PathVariable String registrationUUID, @RequestBody CommentRequestDto commentRequestDto) {
        var event = eventService.getById(eventID);
        var registrationOpt = registrationService.getByUUID(registrationUUID);
        try {
            if (registrationOpt.isEmpty()) throw new NoSuchElementException();
            if (event.isEmpty()) throw new NoSuchElementException();

            Registration registration = registrationOpt.get();
            commentService.create(registration.getID(), commentRequestDto);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
