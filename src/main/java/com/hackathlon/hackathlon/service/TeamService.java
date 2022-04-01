package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> getAll();
    Optional<Team> getById(Long id);
}
