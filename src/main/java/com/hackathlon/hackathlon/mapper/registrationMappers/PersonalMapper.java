package com.hackathlon.hackathlon.mapper.registrationMappers;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.PersonalRequestDto;
import com.hackathlon.hackathlon.entity.User.User;
import org.mapstruct.*;

@Mapper(
        uses = {
                EducationMapper.class
        },
        builder = @Builder(disableBuilder = true))
public interface PersonalMapper {
    @Mapping(source = "name.first", target = "basicInfo.firstName")
    @Mapping(source = "name.last", target = "basicInfo.lastName")
    @Mapping(source = "email", target = "basicInfo.email")
    @Mapping(source = "phone", target = "basicInfo.phone")
    @Mapping(source = "summary", target = "fluff.summary")
    User toEntity(PersonalRequestDto dto);

    @Mapping(source = "basicInfo.firstName", target = "name.first")
    @Mapping(source = "basicInfo.lastName", target = "name.last")
    @Mapping(source = "basicInfo.email", target = "email")
    @Mapping(source = "basicInfo.phone", target = "phone")
    @Mapping(source = "fluff.summary", target = "summary")
    PersonalRequestDto toDto(User user);

    @AfterMapping
    default void mapId(@MappingTarget User user) {
        user.getEducations().forEach(education -> education.setUser(user));
    }
}
