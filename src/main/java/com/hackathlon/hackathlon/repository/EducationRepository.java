package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.user.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findAllByUserID(Long userID);
}
