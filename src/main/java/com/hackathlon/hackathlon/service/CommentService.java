package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;

import java.util.*;

public interface CommentService {
    List<Comment> getAll();
    Optional<Comment> getById(Long id);

    Comment create(Long registrationId, CommentRequestDto commentRequestDto) throws NoSuchElementException;
//    TODO: add UserResponseDto create(UserRequestDto dto);
}
