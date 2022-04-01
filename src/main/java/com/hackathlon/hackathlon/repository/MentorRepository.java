package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findAllByTeamID(Long teamId);
}
