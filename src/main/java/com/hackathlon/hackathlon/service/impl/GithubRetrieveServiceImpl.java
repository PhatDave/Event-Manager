package com.hackathlon.hackathlon.service.impl;


import com.hackathlon.hackathlon.dto.githubRelatedDtos.*;
import com.hackathlon.hackathlon.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

import java.util.List;

@Service
public class GithubRetrieveServiceImpl implements GithubRetrieveService {
    private final WebClient webClient;

    @Value("${githubRoot}")
    private String githubRoot;

    public GithubRetrieveServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("").build();
    }

    public List<GithubRepoDto> get(String name) {
        String uri = "https://api.github.com/users/" + name + "/repos";
        List<GithubRepoDto> dto = runWebClient(uri);
        return dto;
    }

    @Override
    public List<GithubRepoDto> getByUrl(String url) {
        List<GithubRepoDto> dto = runWebClient(url);
        return dto;
    }

    private List<GithubRepoDto> runWebClient(String uri) {
        List<GithubRepoDto> dto = this.webClient
                .get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<GithubRepoDto>>() {
                })
                .block()
                .getBody();
        return dto;
    }
}
