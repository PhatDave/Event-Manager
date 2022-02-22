package com.hackathlon.hackathlon.dto.responses.eventDtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private List<String> emails;
//    TODO: implement default mapping for user -> string
}
