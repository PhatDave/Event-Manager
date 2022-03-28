package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.entity.user.User;
import com.hackathlon.hackathlon.service.GithubGradingService;
import com.hackathlon.hackathlon.service.GithubRetrieveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubGradingServiceImpl implements GithubGradingService {
    private final GithubRetrieveService githubRetrieveService;

    @Override
    public Integer grade(User user) {
        String repositoryUrl = user.getExperience().getRepositoryUrl();
        githubRetrieveService.get(repositoryUrl);


        return null;
    }
}
