package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.user.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findAllByUserID(Long userID);
}
