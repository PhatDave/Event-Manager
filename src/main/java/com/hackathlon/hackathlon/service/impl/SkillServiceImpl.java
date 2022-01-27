package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.user.*;
import com.hackathlon.hackathlon.mapper.registrationMappers.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;

import java.util.*;

@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Override
    public List<Skill> getAll() {
        return this.skillRepository.findAll();
    }

    @Override
    public Optional<Skill> getByID(Long ID) {
        return this.skillRepository.findById(ID);
    }

    @Override
    public Skill create(Long eventID, SkillRequestDto dto) {
        Skill skill = skillMapper.toEntity(dto);
        Skill savedSkill = skillRepository.save(skill);
        return savedSkill;
    }
}
