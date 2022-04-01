package com.hackathlon.hackathlon.dto.requests.eventDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDto {
    private String name;
    private List<MentorRequestDto> mentors;
}
