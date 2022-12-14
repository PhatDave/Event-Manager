package com.hackathlon.repository;

import com.hackathlon.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findAllByTeamID(Long teamId);
}
