package com.hackathlon.service.impl;

import com.hackathlon.dto.requests.registrationDtos.SkillRequestDto;
import com.hackathlon.entity.user.Skill;
import com.hackathlon.mapper.registrationMappers.SkillMapper;
import com.hackathlon.repository.SkillRepository;
import com.hackathlon.service.SkillService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

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
