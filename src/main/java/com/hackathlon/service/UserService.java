package com.hackathlon.service;

import com.hackathlon.entity.user.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
}
