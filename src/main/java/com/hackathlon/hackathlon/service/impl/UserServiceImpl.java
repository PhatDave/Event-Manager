package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.User.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return this.userRepository.findById(id);
    }
}
