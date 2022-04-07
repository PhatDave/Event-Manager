package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAll();
    Team getById(Long id);
    List<Team> getByEventId(Long id);
}
