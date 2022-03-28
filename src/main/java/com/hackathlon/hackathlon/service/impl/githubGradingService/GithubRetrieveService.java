package com.hackathlon.hackathlon.service.impl.githubGradingService;

import com.hackathlon.hackathlon.dto.githubRelatedDtos.GithubRepoDto;

import java.util.List;

public interface GithubRetrieveService {
    List<GithubRepoDto> get(String name);
    List<GithubRepoDto> getByUrl(String url);
}
