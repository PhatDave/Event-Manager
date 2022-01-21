package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.User.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEducationId(Long educationId);
    User findByExperienceId(Long experienceId);
    User findByRegistrationId(Long registrationId);
    User findByWeekId(Long weekId);
    List<User> findAllByTeamId(Long teamId);
}