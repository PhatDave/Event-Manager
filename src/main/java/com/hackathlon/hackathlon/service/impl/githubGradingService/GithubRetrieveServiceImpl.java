package com.hackathlon.hackathlon.service.impl.githubGradingService;


import com.hackathlon.hackathlon.dto.githubRelatedDtos.GithubRepoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
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
        try {
            String name = getNameFromUrl(url);
            return get(name);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private String getNameFromUrl(String url) {
        String name = url.split("https://github.com/")[1];
        return name;
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
