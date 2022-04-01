package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<Registration> findByCommentsID(Long commentId);
    Optional<Registration> findByUserID(Long userId);
    Optional<Registration> findByUUID(String UUID);
    List<Registration> findAllByEventID(Long eventId);
    Page<Registration> findAllByEventID(Long eventId, Pageable pageable);
    List<Registration> findAllByEventIDOrderByScoreDesc(Long eventId);
}
