package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.entity.user.*;
import com.hackathlon.hackathlon.enums.*;
import com.hackathlon.hackathlon.mapper.registrationMappers.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final MultipleRegistrationMapper multipleRegistrationMapper;
    private final EventRepository eventRepository;

    @Override
    public List<Registration> getAll() {
        return this.registrationRepository.findAll();
    }

    @Override
    public RegistrationResponseDto getRegistrationDtoByUUID(String UUID) {
        Optional<Registration> regOpt = registrationRepository.findByUUID(UUID);
        Registration regObj = regOpt.get();
        RegistrationResponseDto dto = registrationMapper.toDto(regObj);
        return dto;
    }

    @Override
    public Optional<Registration> getById(Long id) {
        return this.registrationRepository.findById(id);
    }

    @Override
    public Optional<Registration> getByUUID(String UUID) {
        return this.registrationRepository.findByUUID(UUID);
    }

    @Override
    public void delete(Registration registration) {
        this.registrationRepository.delete(registration);
    }

    @Override
    public Registration create(Long eventID, RegistrationRequestDto dto) {
        Registration reg = registrationMapper.toEntity(dto);
        reg.setEvent(eventRepository.getById(eventID));
        reg.setUUID(UUID.randomUUID().toString());
        this.calculateScore(reg);
        Registration savedReg = registrationRepository.save(reg);
        return savedReg;
    }

    @Override
    public MultipleRegistrationResponseDto getRegistrationsByEventIDPaginated(Long eventID, Integer pageNumber, Integer pageSize) {
        List<Registration> registrations = registrationRepository.findAllByEventIDOrderByScoreDesc(eventID);
        Integer pageStart = pageNumber * pageSize;
        Integer pageEnd = (pageNumber + 1) * pageSize;
//        TODO: error handling
        List<Registration> pageRegistrations = registrations.subList(pageStart, pageEnd);
        List<RegistrationResponseDto> pageRegistrationDtos = pageRegistrations.stream().map(registrationMapper::toDto).collect(Collectors.toList());
        MultipleRegistrationResponseDto dto = multipleRegistrationMapper.toDto(pageRegistrationDtos);
        return dto;
    }

    @Override
    public void calculateScore(Registration registration) {
        Integer score = 0;

        User user = registration.getUser();
        Education education = user.getEducation();
        Experience experience = user.getExperience();

        Integer educationYears = education.getYears();
        Integer experienceYears = experience.getYears();
        List<Skill> skills = experience.getSkills();

        score += educationYears * 2;
        score += experienceYears * 5;

        if (experience.getRepositoryUrl() != null) {
            score += 10;
        }

        for (Skill skill : skills) {
            score += SkillsEnum.applyScore(skill.getName());
        }

        registration.setScore(score);
    }
}
