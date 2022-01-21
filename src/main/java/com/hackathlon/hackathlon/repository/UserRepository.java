package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.User.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEducationsID(Long educationId);
    User findByExperiencesID(Long experienceId);
    User findByRegistrationID(Long registrationId);
    User findByWeeksID(Long weekId);
    List<User> findAllByTeamID(Long teamId);
}