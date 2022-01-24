package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.User.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;

    @Override
    public List<Education> getAll() {
        return this.educationRepository.findAll();
    }

    @Override
    public Optional<Education> getById(Long id) {
        return this.educationRepository.findById(id);
    }
}
