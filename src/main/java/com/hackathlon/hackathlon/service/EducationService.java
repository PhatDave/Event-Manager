package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.user.*;

import java.util.*;

public interface EducationService {
    List<Education> getAll();
    Optional<Education> getById(Long id);
}
