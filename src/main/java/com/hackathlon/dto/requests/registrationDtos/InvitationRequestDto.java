package com.hackathlon.dto.requests.registrationDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvitationRequestDto {
    private Boolean participation;
    private Boolean kickoff;
    @JsonProperty("t-shirt")
    private String tshirt;
//    private String gitlab;
}
