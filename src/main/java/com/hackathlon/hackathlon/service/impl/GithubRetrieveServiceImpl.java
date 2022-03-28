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
        var uri = "https://api.github.com/users/" + name + "/repos";
        var dto = this.webClient
                .get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<GithubRepoDto>>() {})
                .block();
        // todo getBody may produce null??
        return dto.getBody();
    }
}
