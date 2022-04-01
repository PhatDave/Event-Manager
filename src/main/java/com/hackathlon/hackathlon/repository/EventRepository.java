package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByTeamsID(Long teamId);
    Event findByRegistrationsID(Long registrationID);
    Boolean existsByName(String name);
}