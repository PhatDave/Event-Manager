package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.CommentRequestDto;
import com.hackathlon.hackathlon.dto.requests.registrationDtos.InvitationRequestDto;
import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.RegistrationResponseDto;
import com.hackathlon.hackathlon.entity.Event;
import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.service.*;
import com.hackathlon.hackathlon.service.impl.githubGradingService.GithubGradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/event/{eventID}/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final EventService eventService;
    private final UserService userService;
    private final CommentService commentService;
    private final GithubGradingService githubGradingService;
    private final EmailSender emailSender;

    @PostMapping
    private ResponseEntity<?> createRegistration(@PathVariable Long eventID, @RequestBody RegistrationRequestDto registrationRequestDto) {
        Event event = eventService.getById(eventID);
        Registration registration = registrationService.createRegistration(registrationRequestDto, event);
        return ResponseEntity.created(URI.create("/event/" + registration.getEvent().getID() + "/registrations/" + registration.getUUID())).body("");
    }

    @DeleteMapping("/{registrationUUID}")
    private ResponseEntity<?> deleteRegistrationByUUID(@PathVariable Long eventID, @PathVariable String registrationUUID) {
        registrationService.deleteByUUID(registrationUUID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{registrationUUID}")
    private ResponseEntity<RegistrationResponseDto> getRegistrationByUUID(@PathVariable Long eventID, @PathVariable String registrationUUID) {
        RegistrationResponseDto registrationDtoByUUID = registrationService.getRegistrationDtoByUUID(registrationUUID);
        return ResponseEntity.ok(registrationDtoByUUID);
    }

    @GetMapping("")
    private ResponseEntity<Page<RegistrationResponseDto>> getRegistrations(@PathVariable Long eventID, Pageable pageable) {
//        User user = userService.getById(253L);
//        githubGradingService.grade(user);

//        emailSender.sendEmail("david.majdandzic@hotmail.com", "test", "test");

        return ResponseEntity.ok(registrationService.getAllbyEventId(eventID, pageable));
    }

    @PatchMapping("/{registrationUUID}")
    private ResponseEntity<Void> acceptInvitation(@PathVariable Long eventID, @PathVariable String registrationUUID, @RequestBody InvitationRequestDto invitationRequestDto) {
        registrationService.handleInvite(registrationUUID, invitationRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{registrationUUID}/score")
    private ResponseEntity<?> manuallyScoreRegistrationByUUID(@PathVariable Long eventID, @PathVariable String registrationUUID, @RequestBody CommentRequestDto commentRequestDto) {
        Event event = eventService.getById(eventID);
        Registration registration = registrationService.getByUUID(registrationUUID);
        commentService.create(registration.getID(), commentRequestDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
