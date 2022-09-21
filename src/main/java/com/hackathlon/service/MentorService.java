package com.hackathlon.service;

import com.hackathlon.entity.Mentor;

import java.util.List;
import java.util.Optional;

public interface MentorService {
    List<Mentor> getAll();
    Optional<Mentor> getById(Long id);
}
