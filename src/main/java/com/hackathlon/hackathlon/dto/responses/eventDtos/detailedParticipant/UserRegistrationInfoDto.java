package com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationInfoDto {
    Boolean participation;
    Boolean kickoff;
    String tshirt;
}
