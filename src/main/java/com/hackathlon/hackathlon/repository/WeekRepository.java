package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.User.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface WeekRepository extends JpaRepository<Week, Long> {
    List<Week> findAllByUserID(Long userID);
}