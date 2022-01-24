package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.User.*;

import java.util.*;

public interface EducationService {
    List<Education> getAllByUserID(Long userID);

    List<Education> getAll();
    Education getById(Long id);
}
