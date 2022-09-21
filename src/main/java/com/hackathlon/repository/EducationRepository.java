package com.hackathlon.repository;

import com.hackathlon.entity.user.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findAllByUserID(Long userID);
}
