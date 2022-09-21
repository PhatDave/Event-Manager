package com.hackathlon.dto.requests.eventDtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamRequestDto {
    private String name;
    private List<MentorRequestDto> mentors;
}
