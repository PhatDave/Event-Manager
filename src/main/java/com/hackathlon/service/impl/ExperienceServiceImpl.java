package com.hackathlon.service.impl;

import com.hackathlon.entity.user.Experience;
import com.hackathlon.repository.ExperienceRepository;
import com.hackathlon.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;

    @Override
    public List<Experience> getAll() {
        return this.experienceRepository.findAll();
    }

    @Override
    public Optional<Experience> getById(Long id) {
        return this.experienceRepository.findById(id);
    }
}
