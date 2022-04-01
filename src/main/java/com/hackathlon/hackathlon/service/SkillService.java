package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.SkillRequestDto;
import com.hackathlon.hackathlon.entity.user.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    List<Skill> getAll();
    Optional<Skill> getByID(Long ID);
    Skill create(Long eventID, SkillRequestDto dto);
}
