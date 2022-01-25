package com.hackathlon.hackathlon.dto.requests;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDto {
    private String name;
    private List<MentorRequestDto> mentors;
}