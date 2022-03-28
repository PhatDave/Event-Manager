package com.hackathlon.hackathlon.dto.githubRelatedDtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepoDto {
    private Long id;
    private String name;
    private String language;
    private Date updated_at;
}
