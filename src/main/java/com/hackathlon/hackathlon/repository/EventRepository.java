package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.jpa.repository.*;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByTeamId(Long teamId);
    Event findByRegistrationId(Long registrationId);
}