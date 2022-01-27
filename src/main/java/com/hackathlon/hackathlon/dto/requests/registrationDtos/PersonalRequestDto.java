package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalRequestDto {
    private NameRequestDto name;
    private String email;
    private String phone;
    private List<EducationRequestDto> educations;
    private String summary;
}
