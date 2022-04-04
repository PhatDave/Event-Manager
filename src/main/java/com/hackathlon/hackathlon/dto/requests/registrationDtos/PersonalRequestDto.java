package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalRequestDto {
    private NameRequestDto name;
    private String email;
    private String phone;
    private EducationRequestDto education;
    private String summary;
}
