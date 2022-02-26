package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceRequestDto {
    private Integer years;
    private List<SkillRequestDto> skills;
    private String repositoryUrl;
    private String summary;
}
