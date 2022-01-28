package com.hackathlon.hackathlon.enums;

import lombok.*;

@Getter
public enum SkillsEnum {
    JAVA("Java", 20),
    SPRING("Spring", 20),
    SPRING_BOOT("Spring Boot", 20),
    HIBERNATE("Hibernate", 10),
    JPA("JPA", 10),
    SCALA("Scala", 10);

    private String name;
    private Integer score;

    SkillsEnum(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    String getDisplayValue() {
        return this.name;
    }

    public static Integer applyScore(String skill) {
        for (SkillsEnum enumitem : SkillsEnum.values()) {
            if (enumitem.getName().equals(skill)) {
                return enumitem.getScore();
            }
        }
        return 5;
    }
}
