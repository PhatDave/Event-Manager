package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.repository.RegistrationRepository;
import com.hackathlon.hackathlon.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;

    @Override
    public List<Registration> getAll() {
        return this.registrationRepository.findAll();
    }

    @Override
    public Optional<Registration> getById(Long id) {
        return this.registrationRepository.findById(id);
    }

    @Override
    public Registration create(Long eventID, RegistrationRequestDto registrationRequestDto) {
        // TODO set event, map dto, save entity
        return null;
    }
}
