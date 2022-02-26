package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequestDto {
    private String faculty;
    private Integer year;
}
