package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.User.*;

import java.util.*;

public interface EducationService {
    List<Education> getAll();
    Education getById(Long id);
}
