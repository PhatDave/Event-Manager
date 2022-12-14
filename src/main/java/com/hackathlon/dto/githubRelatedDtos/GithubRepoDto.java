package com.hackathlon.dto.githubRelatedDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
