package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.*;
import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.user.*;
import com.hackathlon.hackathlon.enums.*;
import com.hackathlon.hackathlon.mapper.registrationMappers.*;
import com.hackathlon.hackathlon.repository.*;
import com.hackathlon.hackathlon.service.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;

import java.util.*;

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
        Registration registration = registrationRepository.findByUUID(UUID).orElseThrow(() -> new NoSuchElementException("Registration with UUID " + UUID + " not found"));
        RegistrationResponseDto dto = registrationMapper.toDto(registration);
        return dto;
    }

    @Override
    public Registration getById(Long id) {
        return this.registrationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Registration with id " + id + " not found"));
    }

    @Override
    public Registration getByUUID(String UUID) {
        return this.registrationRepository.findByUUID(UUID).orElseThrow(() -> new NoSuchElementException("Registration with UUID " + UUID + " not found"));
    }

    @Override
    public void deleteByUUID(String UUID) {
        this.registrationRepository.findByUUID(UUID).ifPresent(this::delete);
    }

    @Override
    public Registration createRegistration(RegistrationRequestDto registrationRequestDto, Event event) {
        Date today = Calendar.getInstance().getTime();
        if (event.getRegistrationsNotAfter().after(today)) {
            throw new IllegalStateException("Registration is not allowed after " + event.getRegistrationsNotAfter());
        }
        Registration registration = this.create(event.getID(), registrationRequestDto);
        return registration;
    }

    @Override
    public void delete(Registration registration) {
        this.registrationRepository.deleteById(registration.getID());
//        this.registrationRepository.delete(registration);
    }

    @Override
    public Registration create(Long eventID, RegistrationRequestDto dto) {
        Registration reg = registrationMapper.toEntity(dto);
        reg.setEvent(eventRepository.getById(eventID));
        reg.setUUID(UUID.randomUUID().toString());
        reg.setStatus(RegistrationStatusEnum.NOT_INVITED);
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
    public void handleInvite(String registrationUUID, InvitationRequestDto invitationRequestDto) {
        Registration registration = this.registrationRepository.findByUUID(registrationUUID).orElseThrow(() -> new IllegalArgumentException("Registration with UUID " + registrationUUID + " not found"));
        validateRegistration(registration);

        registrationMapper.merge(invitationRequestDto, registration);
        registration.setStatus(RegistrationStatusEnum.ACCEPTED);
        registrationRepository.save(registration);
    }

    private void validateRegistration(Registration registration) {
        if (registration.getStatus() == RegistrationStatusEnum.ACCEPTED) {
            throw new IllegalStateException("Registration with UUID " + registration.getUUID() + " is already accepted");
        } else if (registration.getStatus() != RegistrationStatusEnum.INVITED) {
            throw new IllegalStateException("Registration is not INVITED and can not be ACCEPTED");
        }
    }
}
