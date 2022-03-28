package com.hackathlon.hackathlon.controller;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.user.User;
import com.hackathlon.hackathlon.service.*;
import com.hackathlon.hackathlon.service.impl.githubGradingService.GithubGradingService;
import com.hackathlon.hackathlon.service.impl.githubGradingService.GithubRetrieveService;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.*;
import java.util.Optional;

//TODO: send emails
//TODO: get github api thing

@RestController
@RequestMapping("/event/{eventID}/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final EventService eventService;
    private final UserService userService;
    private final CommentService commentService;
    private final GithubGradingService githubGradingService;

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
        User user = userService.getById(253L);
        githubGradingService.grade(user);
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
