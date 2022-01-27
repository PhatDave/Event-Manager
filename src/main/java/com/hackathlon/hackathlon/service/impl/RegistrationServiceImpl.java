package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.mapper.registrationMappers.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final EventRepository eventRepository;

    @Override
    public List<Registration> getAll() {
        return this.registrationRepository.findAll();
    }

    @Override
    public Optional<Registration> getById(Long id) {
        return this.registrationRepository.findById(id);
    }

    @Override
    public Registration create(Long eventID, RegistrationRequestDto dto) {
        Registration reg = registrationMapper.toEntity(dto);
        reg.setEvent(eventRepository.getById(eventID));
        reg.setUUID(UUID.randomUUID().toString());
        Registration savedReg = registrationRepository.save(reg);
        return savedReg;
    }
}
