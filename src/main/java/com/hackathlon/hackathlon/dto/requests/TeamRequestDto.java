package com.hackathlon.hackathlon.dto.requests;

import com.hackathlon.hackathlon.entity.*;
import lombok.*;
import org.mapstruct.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDto {
    private String name;
    private List<MentorRequestDto> mentors;
}
