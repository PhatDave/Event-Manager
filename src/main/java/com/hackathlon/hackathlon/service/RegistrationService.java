package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.domain.*;

import java.util.*;

public interface RegistrationService {
    List<Registration> getAll();
    RegistrationResponseDto getRegistrationDtoByUUID(String UUID);
    Optional<Registration> getById(Long id);
    Optional<Registration> getByUUID(String UUID);

    void delete(Registration registration);
    Registration create(Long eventID, RegistrationRequestDto registrationRequestDto);

    void calculateScore(Registration registration);

    Page<RegistrationResponseDto> getAllbyEventId(Long eventID, Pageable pageable);

    void handleInvite(String registrationUUID, InvitationRequestDto invitationRequestDto);
}
