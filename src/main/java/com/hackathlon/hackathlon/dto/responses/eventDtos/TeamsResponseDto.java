package com.hackathlon.hackathlon.dto.responses.eventDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamsResponseDto {
    List<TeamResponseDto> teams;
}
