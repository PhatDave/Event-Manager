package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.user.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEducationID(Long educationId);
    User findByExperienceID(Long experienceId);
    User findByRegistrationID(Long registrationId);
    User findByWeeksID(Long weekId);
    List<User> findAllByTeamID(Long teamId);
}