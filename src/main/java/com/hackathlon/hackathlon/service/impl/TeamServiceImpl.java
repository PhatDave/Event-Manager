package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.Team;
import com.hackathlon.hackathlon.repository.TeamRepository;
import com.hackathlon.hackathlon.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
