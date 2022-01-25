package com.hackathlon.hackathlon.dto.requests;

import com.hackathlon.hackathlon.entity.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorRequestDto {
    private Team team;
    private String email;
}
