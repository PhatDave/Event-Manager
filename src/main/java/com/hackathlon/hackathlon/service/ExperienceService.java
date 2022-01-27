package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.user.*;

import java.util.*;

public interface ExperienceService {
    List<Experience> getAll();
    Optional<Experience> getById(Long id);
}
