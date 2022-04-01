package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.Mentor;

import java.util.List;
import java.util.Optional;

public interface MentorService {
    List<Mentor> getAll();
    Optional<Mentor> getById(Long id);
}
