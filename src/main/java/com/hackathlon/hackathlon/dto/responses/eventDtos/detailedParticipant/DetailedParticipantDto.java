package com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant;

import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailedParticipantDto {
    RegistrationResponseDto registration;
    UserRegistrationInfoDto additionalInfo;
    WeekProgressDto weekProgress;
}