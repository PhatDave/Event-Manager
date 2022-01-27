package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByUsersID(Long userId);
    Team findByMentorsID(Long mentorId);
    List<Team> findAllByEventID(Long eventId);
}
