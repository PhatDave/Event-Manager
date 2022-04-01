package com.hackathlon.hackathlon.dto.responses.registrationDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
