package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.entity.Registration;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    List<Registration> getAll();

    Optional<Registration> getById(Long id);

    Registration create(Long eventID, RegistrationRequestDto registrationRequestDto);
}
