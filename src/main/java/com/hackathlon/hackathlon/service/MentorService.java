package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.*;

import java.util.*;

public interface MentorService {
    List<Mentor> getAll();
    Mentor getById(Long id);
}
