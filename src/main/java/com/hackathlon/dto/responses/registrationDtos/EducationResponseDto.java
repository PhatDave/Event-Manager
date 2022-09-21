package com.hackathlon.dto.responses.registrationDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationResponseDto {
    private String faculty;
    private Integer year;
}
