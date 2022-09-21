package com.hackathlon.service;

import com.hackathlon.entity.user.Education;

import java.util.List;
import java.util.Optional;

public interface EducationService {
    List<Education> getAll();
    Optional<Education> getById(Long id);
}
