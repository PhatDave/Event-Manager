package com.hackathlon.repository;

import com.hackathlon.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByUsersID(Long userId);
    Team findByMentorsID(Long mentorId);
    List<Team> findAllByEventID(Long eventId);
}
