package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.domain.*;

import java.util.*;

public interface RegistrationService {
    List<Registration> getAll();
    RegistrationResponseDto getRegistrationDtoByUUID(String UUID);
    Registration getById(Long id);
    Registration getByUUID(String UUID);
    void deleteByUUID(String UUID);
    Registration createRegistration(RegistrationRequestDto registrationRequestDto, Event event);

    void delete(Registration registration);
    Registration create(Long eventID, RegistrationRequestDto registrationRequestDto);

    void calculateScore(Registration registration);

    Page<RegistrationResponseDto> getAllbyEventId(Long eventID, Pageable pageable);

    void handleInvite(String registrationUUID, InvitationRequestDto invitationRequestDto);
}
