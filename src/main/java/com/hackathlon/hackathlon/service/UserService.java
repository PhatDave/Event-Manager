package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.User.*;

import java.util.*;

public interface UserService {
    List<Experience> getAllExperiences(User user);
    List<Education> getAllEducations(User user);
    Team getTeam(User user);
    Registration getRegistration(User user);
    List<Week> getAllWeeks(User user);

    List<User> getAll();
    Optional<User> getById(Long id);
}
