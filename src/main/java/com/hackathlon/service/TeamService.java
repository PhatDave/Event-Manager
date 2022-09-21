package com.hackathlon.service;

import com.hackathlon.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAll();
    Team getById(Long id);
    List<Team> getByEventId(Long id);
}
