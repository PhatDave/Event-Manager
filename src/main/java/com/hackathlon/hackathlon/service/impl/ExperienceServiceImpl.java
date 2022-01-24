package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.User.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

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
