package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvitationRequestDto {
    private Boolean participation;
    private Boolean kickoff;
    @JsonProperty("t-shirt")
    private String tshirt;
//    private String gitlab;
}
