package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Registration findByCommentId(Long commentId);
    Registration findByUserId(Long userId);
    List<Registration> findAllByEventId(Long eventId);
}
