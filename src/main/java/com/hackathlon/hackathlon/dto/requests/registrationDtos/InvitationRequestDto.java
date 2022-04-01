package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
