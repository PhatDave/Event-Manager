package com.hackathlon.hackathlon.dto.responses.eventDtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponseDto {
    private String name;
    private List<String> members;
}
