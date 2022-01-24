package com.hackathlon.hackathlon.dto;

import com.hackathlon.hackathlon.entity.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDto {
    private String name;
    private Integer maxParticipants;
    private List<Team> teams;
    private Date registrationsNotBefore;
    private Date registrationsNotAfter;
    private Date confirmationNotAfter;
    private Date startDate;
    private Integer weeks;
}
