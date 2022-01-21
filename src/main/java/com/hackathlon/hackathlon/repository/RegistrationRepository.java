package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Registration findByCommentsID(Long commentId);
    Registration findByUserID(Long userId);
    List<Registration> findAllByEventID(Long eventId);
}
