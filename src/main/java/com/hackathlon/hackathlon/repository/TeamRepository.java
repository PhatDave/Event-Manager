package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface TeamRepository extends JpaRepository<Team, Long> {
//    TODO: It wants user(s!) while experience & education use userID(!)??????????????????????
    Team findByUsersID(Long userId);
    Team findByMentorsID(Long mentorId);
    List<Team> findAllByEventID(Long eventId);
}
