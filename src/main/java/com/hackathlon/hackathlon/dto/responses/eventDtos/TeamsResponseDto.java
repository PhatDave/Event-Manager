package com.hackathlon.hackathlon.dto.responses.eventDtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamsResponseDto {
    List<TeamResponseDto> teams;
}
