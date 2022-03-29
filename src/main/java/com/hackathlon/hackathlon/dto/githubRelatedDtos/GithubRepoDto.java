package com.hackathlon.hackathlon.dto.githubRelatedDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepoDto {
    private Long id;
    private String name;
    private String language;
}
