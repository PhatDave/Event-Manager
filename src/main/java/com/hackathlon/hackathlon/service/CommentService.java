package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.*;

import java.util.*;

public interface CommentService {
    List<Comment> getAll();
    Optional<Comment> getById(Long id);
//    TODO: add UserResponseDto create(UserRequestDto dto);
}
