package com.hackathlon.hackathlon.repository;

import com.hackathlon.hackathlon.entity.user.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}
