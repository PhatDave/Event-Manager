package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.Registration;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    List<Registration> getAll();
    RegistrationResponseDto getRegistrationDtoByUUID(String UUID);
    Optional<Registration> getById(Long id);
    Optional<Registration> getByUUID(String UUID);

    void delete(Registration registration);
    Registration create(Long eventID, RegistrationRequestDto registrationRequestDto);

    void calculateScore(Registration registration);
}
