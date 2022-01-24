package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.*;

import java.util.*;

public interface RegistrationService {
    List<Registration> getAll();
    Optional<Registration> getById(Long id);
}
