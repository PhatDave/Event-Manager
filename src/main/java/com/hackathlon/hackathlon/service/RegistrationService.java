package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.InvitationRequestDto;
import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.RegistrationResponseDto;
import com.hackathlon.hackathlon.entity.Event;
import com.hackathlon.hackathlon.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
