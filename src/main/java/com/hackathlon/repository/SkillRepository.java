package com.hackathlon.repository;

import com.hackathlon.entity.user.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}
