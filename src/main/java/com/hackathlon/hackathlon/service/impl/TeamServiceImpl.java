package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.Team;
import com.hackathlon.hackathlon.repository.TeamRepository;
import com.hackathlon.hackathlon.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Team with id " + id + " not found"));
    }

    @Override
    public List<Team> getByEventId(Long id) {
        return teamRepository.findAllByEventID(id);
    }
}
