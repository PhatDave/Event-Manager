package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.User.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findAllByUserID(Long userID);
}
