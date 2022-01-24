package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.*;

import java.util.*;

public interface TeamService {
    List<Team> getAll();
    Optional<Team> getById(Long id);
}
