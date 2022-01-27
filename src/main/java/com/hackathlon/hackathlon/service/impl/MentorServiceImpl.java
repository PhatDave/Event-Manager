package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {
    private final MentorRepository mentorRepository;

    @Override
    public List<Mentor> getAll() {
        return this.mentorRepository.findAll();
    }

    @Override
    public Optional<Mentor> getById(Long id) {
        return this.mentorRepository.findById(id);
    }
}
