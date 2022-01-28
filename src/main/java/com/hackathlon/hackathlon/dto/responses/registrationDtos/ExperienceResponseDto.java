package com.hackathlon.hackathlon.dto.responses.registrationDtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceResponseDto {
    private Integer years;
    private List<SkillResponseDto> skills;
    private String repositoryUrl;
    private String summary;
}
