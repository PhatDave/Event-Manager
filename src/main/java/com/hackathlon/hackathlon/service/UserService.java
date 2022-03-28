package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.user.*;

import java.util.*;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
}
