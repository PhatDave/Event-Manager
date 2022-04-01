package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.CommentRequestDto;
import com.hackathlon.hackathlon.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAll();
    Optional<Comment> getById(Long id);

    Comment create(Long registrationId, CommentRequestDto commentRequestDto);
}
