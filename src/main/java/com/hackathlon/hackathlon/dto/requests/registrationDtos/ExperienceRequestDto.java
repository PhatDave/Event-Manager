package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienceRequestDto {
    private Integer years;
    private List<SkillRequestDto> skills;
    private String repositoryUrl;
    private String summary;
}
