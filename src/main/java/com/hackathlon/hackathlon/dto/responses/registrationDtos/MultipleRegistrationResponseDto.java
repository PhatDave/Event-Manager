package com.hackathlon.hackathlon.dto.responses.registrationDtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultipleRegistrationResponseDto {
    private List<RegistrationResponseDto> content;
}
