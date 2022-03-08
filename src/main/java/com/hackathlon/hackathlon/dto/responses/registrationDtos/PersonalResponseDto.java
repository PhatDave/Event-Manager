package com.hackathlon.hackathlon.dto.responses.registrationDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalResponseDto {
    private NameResponseDto name;
    private String email;
    private String phone;
    private EducationResponseDto education;
    private String summary;
}