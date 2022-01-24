package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

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
}
