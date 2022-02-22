package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvitationRequestDto {
    private Boolean participation;
    private Boolean kickoff;
    private String tshirt;
    private String gitlab;
}
