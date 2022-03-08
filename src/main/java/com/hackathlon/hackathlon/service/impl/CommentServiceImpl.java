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
    public Comment create(Long registrationId, CommentRequestDto commentRequestDto) throws NoSuchElementException {
        Comment comment = commentMapper.toEntity(commentRequestDto);
        Optional<Registration> registration = registrationRepository.findById(registrationId);
        if (registration.isEmpty()) {
            throw new NoSuchElementException();
        }
        Registration regObj = registration.get();

        comment.setRegistration(regObj);
        int newScore = comment.getScore() + regObj.getScore();
        regObj.setScore(newScore);
        registrationRepository.save(regObj);
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }
}
