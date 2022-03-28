package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.user.User;

public interface GithubGradingService {
    Integer grade(User user);
}
