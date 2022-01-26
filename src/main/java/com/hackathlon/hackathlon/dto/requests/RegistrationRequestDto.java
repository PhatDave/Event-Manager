package com.hackathlon.hackathlon.dto.requests;

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
