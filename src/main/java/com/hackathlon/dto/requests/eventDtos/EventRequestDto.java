package com.hackathlon.dto.requests.eventDtos;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequestDto {
//    @JsonProperty("id")
    private String name;
    private Integer maxParticipants;
    private List<TeamRequestDto> teams;
    private Date registrationsNotBefore;
    private Date registrationsNotAfter;
    private Date confirmationNotAfter;
    private Date startDate;
    private Integer weeks;
}
