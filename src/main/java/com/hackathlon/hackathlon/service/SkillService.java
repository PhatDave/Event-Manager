package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.dto.requests.registrationDtos.*;
import com.hackathlon.hackathlon.entity.user.*;

import java.util.*;

public interface SkillService {
    List<Skill> getAll();
    Optional<Skill> getByID(Long ID);
    Skill create(Long eventID, SkillRequestDto dto);
}
