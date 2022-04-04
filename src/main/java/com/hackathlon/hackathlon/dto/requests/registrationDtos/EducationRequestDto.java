package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationRequestDto {
    private String faculty;
    private Integer year;
}
