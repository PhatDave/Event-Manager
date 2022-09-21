package com.hackathlon.service;

import com.hackathlon.dto.requests.registrationDtos.SkillRequestDto;
import com.hackathlon.entity.user.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    List<Skill> getAll();
    Optional<Skill> getByID(Long ID);
    Skill create(Long eventID, SkillRequestDto dto);
}
