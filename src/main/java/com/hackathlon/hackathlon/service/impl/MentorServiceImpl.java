package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.Mentor;
import com.hackathlon.hackathlon.repository.MentorRepository;
import com.hackathlon.hackathlon.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
