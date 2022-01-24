package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.User.*;

import java.util.*;

public interface EventService {
    List<Event> getAll();
    Event getById(Long id);
}
