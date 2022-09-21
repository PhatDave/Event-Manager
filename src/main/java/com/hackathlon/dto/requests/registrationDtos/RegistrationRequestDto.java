package com.hackathlon.dto.requests.registrationDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequestDto {
    private PersonalRequestDto personal;
    private ExperienceRequestDto experience;
    private String motivation;
    private String preferredOS;
}
