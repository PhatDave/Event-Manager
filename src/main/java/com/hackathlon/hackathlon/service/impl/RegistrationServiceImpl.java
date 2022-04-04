package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.InvitationRequestDto;
import com.hackathlon.hackathlon.dto.requests.registrationDtos.RegistrationRequestDto;
import com.hackathlon.hackathlon.dto.responses.registrationDtos.RegistrationResponseDto;
import com.hackathlon.hackathlon.entity.Event;
import com.hackathlon.hackathlon.entity.Registration;
import com.hackathlon.hackathlon.entity.user.Education;
import com.hackathlon.hackathlon.entity.user.Experience;
import com.hackathlon.hackathlon.entity.user.Skill;
import com.hackathlon.hackathlon.entity.user.User;
import com.hackathlon.hackathlon.enums.RegistrationStatusEnum;
import com.hackathlon.hackathlon.enums.SkillsEnum;
import com.hackathlon.hackathlon.mapper.registrationMappers.RegistrationMapper;
import com.hackathlon.hackathlon.repository.RegistrationRepository;
import com.hackathlon.hackathlon.service.EmailSender;
import com.hackathlon.hackathlon.service.EventService;
import com.hackathlon.hackathlon.service.RegistrationService;
import com.hackathlon.hackathlon.service.impl.githubGradingService.GithubGradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final EventService eventService;
    private final GithubGradingService githubGradingService;
    private final EmailSender emailSender;

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
        Date today = new Date();
        if (event.getRegistrationsNotAfter().before(today)) {
            throw new IllegalStateException("Registration is not allowed after " + event.getRegistrationsNotAfter());
        }
        emailSender.sendEmail(registrationRequestDto.getPersonal().getEmail(), "Registration", "You have successfully registered for " + event.getName());
        Registration registration = this.create(event.getID(), registrationRequestDto);
        return registration;
    }

    @Override
    public void delete(Registration registration) {
        if (!this.registrationRepository.existsById(registration.getID())) {
            throw new NoSuchElementException("Registration with id " + registration.getID() + " not found");
        }
        this.registrationRepository.deleteById(registration.getID());
//        this.registrationRepository.delete(registration);
    }

    @Override
    public Registration create(Long eventID, RegistrationRequestDto dto) {
        Registration reg = registrationMapper.toEntity(dto);
        reg.setEvent(eventService.getById(eventID));
        reg.setUUID(UUID.randomUUID().toString());
        reg.setStatus(RegistrationStatusEnum.NOT_INVITED);
        calculateScore(reg);
        reg.setScore(reg.getScore() + githubGradingService.grade(reg.getUser()));
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
        emailSender.sendEmail(registration.getUser().getBasicInfo().getEmail(), "Invitation", "You have been invited to " + registration.getEvent().getName());
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
