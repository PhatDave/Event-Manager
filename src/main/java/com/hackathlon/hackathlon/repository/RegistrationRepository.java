package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<Registration> findByCommentsID(Long commentId);
    Optional<Registration> findByUserID(Long userId);
    Optional<Registration> findByUUID(String UUID);
    List<Registration> findAllByEventID(Long eventId);
}
