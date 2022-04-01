package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.user.Education;
import com.hackathlon.hackathlon.repository.EducationRepository;
import com.hackathlon.hackathlon.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
