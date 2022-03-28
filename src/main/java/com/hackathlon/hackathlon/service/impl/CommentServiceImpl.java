package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.mapper.registrationMappers.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final RegistrationRepository registrationRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<Comment> getAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getById(Long id) {
        return this.commentRepository.findById(id);
    }

    @Override
    public Comment create(Long registrationId, CommentRequestDto commentRequestDto) {
        Comment comment = commentMapper.toEntity(commentRequestDto);
        Registration registration = registrationRepository.findById(registrationId).orElseThrow(() -> new NoSuchElementException("Registration with id " + registrationId + " not found"));

        comment.setRegistration(registration);
        int newScore = comment.getScore() + registration.getScore();
        registration.setScore(newScore);
        registrationRepository.save(registration);
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }
}
