package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import org.mapstruct.*;

@Mapper(builder = @Builder(disableBuilder=true))
public interface CommentMapper {
    @Mapping(source = "comment", target = "content")
    Comment toEntity(CommentRequestDto dto);

    // I feel like this really belongs here but setRegistration is misplaced in controller
//    @AfterMapping
//    default void convertScoreToInteger(CommentRequestDto dto, @MappingTarget Comment comment) {
//        Integer intScore = Integer.parseInt(dto.getScore());
//        comment.setScore(intScore);
//        Registration registration = comment.getRegistration();
//        var newScore = intScore + registration.getScore();
//        registration.setScore(newScore);
//    }
    @AfterMapping
    default void convertScoreToInteger(CommentRequestDto dto, @MappingTarget Comment comment) {
        Integer intScore = Integer.parseInt(dto.getScore());
        comment.setScore(intScore);
    }


    CommentRequestDto toDto(Comment dto);
}
