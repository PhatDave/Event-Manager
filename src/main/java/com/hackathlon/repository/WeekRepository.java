package com.hackathlon.repository;

import com.hackathlon.entity.user.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekRepository extends JpaRepository<Week, Long> {
    List<Week> findAllByUserID(Long userID);
}
