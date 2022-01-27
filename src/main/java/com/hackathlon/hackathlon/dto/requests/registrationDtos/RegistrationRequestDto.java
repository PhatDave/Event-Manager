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
public class RegistrationRequestDto {
    private PersonalRequestDto personal;
    private List<ExperienceRequestDto> experiences;
    private String motivation;
    private String preferredOS;
}
