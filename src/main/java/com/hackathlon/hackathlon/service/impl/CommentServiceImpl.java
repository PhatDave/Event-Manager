package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.CommentRequestDto;
import com.hackathlon.hackathlon.entity.Comment;
import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.mapper.registrationMappers.CommentMapper;
import com.hackathlon.hackathlon.repository.CommentRepository;
import com.hackathlon.hackathlon.repository.RegistrationRepository;
import com.hackathlon.hackathlon.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
