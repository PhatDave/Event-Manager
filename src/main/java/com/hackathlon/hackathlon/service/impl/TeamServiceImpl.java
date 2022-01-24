package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public List<Team> getAll() {
        return this.teamRepository.findAll();
    }

    @Override
    public Optional<Team> getById(Long id) {
        return this.teamRepository.findById(id);
    }
}
