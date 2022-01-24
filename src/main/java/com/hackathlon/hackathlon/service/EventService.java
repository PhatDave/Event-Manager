package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.User.*;

import java.util.*;

public interface EventService {
    List<Event> getAll();
    Optional<Event> getById(Long id);
    Event create(EventRequestDto dto);
}
