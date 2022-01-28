package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(builder = @Builder(disableBuilder=true))
public interface CommentMapper {
    @Mapping(source = "comment", target = "content")
    Comment toEntity(CommentRequestDto dto);

    @Mapping(source = "content", target = "comment")
    CommentResponseDto toDto(Comment comment);

    @AfterMapping
    default void convertScoreToInteger(CommentRequestDto dto, @MappingTarget Comment comment) {
        Integer intScore = Integer.parseInt(dto.getScore());
        comment.setScore(intScore);
    }
}
