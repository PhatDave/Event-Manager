package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.User.*;

import java.util.*;

public interface UserService {
    List<User> getAll();
    Optional<User> getById(Long id);
}
