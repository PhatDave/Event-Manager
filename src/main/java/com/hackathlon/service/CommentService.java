package com.hackathlon.service;

import com.hackathlon.dto.requests.registrationDtos.CommentRequestDto;
import com.hackathlon.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAll();
    Optional<Comment> getById(Long id);

    Comment create(Long registrationId, CommentRequestDto commentRequestDto);
}
