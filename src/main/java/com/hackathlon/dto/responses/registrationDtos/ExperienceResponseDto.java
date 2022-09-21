package com.hackathlon.dto.responses.registrationDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceResponseDto {
    private Integer years;
    private List<String> skills;
    private String repositoryUrl;
    private String summary;
}
