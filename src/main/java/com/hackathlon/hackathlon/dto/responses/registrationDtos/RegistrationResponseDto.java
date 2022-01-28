package com.hackathlon.hackathlon.dto.responses.registrationDtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseDto {
    private PersonalResponseDto personal;
    private ExperienceResponseDto experience;
    private Integer score;
    private List<CommentResponseDto> comments;
}
