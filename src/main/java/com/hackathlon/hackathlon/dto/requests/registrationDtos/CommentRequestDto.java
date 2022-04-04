package com.hackathlon.hackathlon.dto.requests.registrationDtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {
    private String comment;
    private String score;
}
