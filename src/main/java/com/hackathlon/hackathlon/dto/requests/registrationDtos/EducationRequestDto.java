package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequestDto {
    private String faculty;
    private Integer years;
}
