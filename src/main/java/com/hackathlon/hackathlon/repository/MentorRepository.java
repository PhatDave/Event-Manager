package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findAllByTeamId(Long teamId);
}
