package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByUserId(Long userId);
    Team findByMentorId(Long mentorId);
    List<Team> findAllByEventId(Long eventId);
}
