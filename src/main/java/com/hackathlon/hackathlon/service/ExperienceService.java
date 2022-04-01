package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.user.Experience;

import java.util.List;
import java.util.Optional;

public interface ExperienceService {
    List<Experience> getAll();
    Optional<Experience> getById(Long id);
}
