package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.entity.user.*;
import com.hackathlon.hackathlon.enums.*;
import com.hackathlon.hackathlon.mapper.registrationMappers.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

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

    @Override
    public Page<RegistrationResponseDto> getAllbyEventId(Long eventID, Pageable pageable) {
        Page<Registration> registrationpage = registrationRepository.findAllByEventID(eventID, pageable);
        return registrationpage.map(registrationMapper::toDto);
    }

    @Override
    public void handleInvite(String registrationUUID, InvitationRequestDto invitationRequestDto) throws NoSuchElementException, IllegalStateException {
        Registration registration = getRegistrationIfExists(registrationUUID);
        validateRegistration(registration);

        updateRegistration(invitationRequestDto, registration);
        updateRegistrationUser(invitationRequestDto, registration);
    }

    private void validateRegistration(Registration registration) throws IllegalStateException {
        if (registration.getStatus() != RegistrationStatusEnum.INVITED) {
            throw new IllegalStateException();
        }
    }

    private void updateRegistrationUser(InvitationRequestDto invitationRequestDto, Registration registration) {
        User user = registration.getUser();
        user.getFluff().setTShirt(invitationRequestDto.getTshirt());
        userRepository.save(user);
    }

    private void updateRegistration(InvitationRequestDto invitationRequestDto, Registration registration) {
        registration.setKickoff(invitationRequestDto.getKickoff());
        registration.setParticipation(invitationRequestDto.getParticipation());
        registration.setStatus(RegistrationStatusEnum.ACCEPTED);
        registrationRepository.save(registration);
    }

    private Registration getRegistrationIfExists(String registrationUUID) throws NoSuchElementException {
        Optional<Registration> registration = registrationRepository.findByUUID(registrationUUID);
        if (registration.isEmpty()) {
            throw new NoSuchElementException();
        }
        return registration.get();
    }
}
