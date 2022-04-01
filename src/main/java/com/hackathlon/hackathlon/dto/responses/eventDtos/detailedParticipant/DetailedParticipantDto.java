package com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant;

import com.hackathlon.hackathlon.dto.responses.registrationDtos.RegistrationResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailedParticipantDto {
    RegistrationResponseDto registration;
    UserRegistrationInfoDto additionalInfo;
    WeekProgressDto weekProgress;
}
