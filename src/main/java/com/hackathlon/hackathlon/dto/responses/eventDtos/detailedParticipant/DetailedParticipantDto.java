package com.hackathlon.hackathlon.dto.responses.eventDtos.detailedParticipant;

import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailedParticipantDto {
//    TODO: have a wrapper for this one and it's damned content list
    RegistrationResponseDto registration;
    UserRegistrationInfoDto additionalInfo;
    WeekProgressDto weekProgress;
}
