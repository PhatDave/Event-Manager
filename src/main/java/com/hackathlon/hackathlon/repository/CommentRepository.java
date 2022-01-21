package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByRegistrationId(Long registrationId);
}
