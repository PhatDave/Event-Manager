package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NameRequestDto {
    private String first;
    private String last;
}
