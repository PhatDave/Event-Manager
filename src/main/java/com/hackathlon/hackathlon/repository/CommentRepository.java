package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByRegistrationID(Long registrationId);
}
