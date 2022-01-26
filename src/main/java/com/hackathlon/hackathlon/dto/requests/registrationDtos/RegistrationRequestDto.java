package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {
    private PersonalRequestDto personal;
    private ExperienceRequestDto experience;
    private String motivation;
    private String preferredOS;
}
