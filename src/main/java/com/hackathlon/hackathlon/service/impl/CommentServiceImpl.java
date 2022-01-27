package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getById(Long id) {
        return this.commentRepository.findById(id);
    }
}
