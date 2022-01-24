package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.*;

import java.util.*;

public interface EventService {
    List<Event> getAll();
    Optional<Event> getById(Long id);
}
