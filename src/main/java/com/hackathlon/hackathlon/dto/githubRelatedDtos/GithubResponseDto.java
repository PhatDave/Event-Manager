package com.hackathlon.hackathlon.dto.githubRelatedDtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GithubResponseDto {
    List<GithubRepoDto> repos;
}
