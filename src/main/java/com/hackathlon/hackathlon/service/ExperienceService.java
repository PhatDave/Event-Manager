package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.User.*;

import java.util.*;

public interface ExperienceService {
    List<Experience> getAllByUserID(Long userID);

    List<Experience> getAll();
    Experience getById(Long id);
}
