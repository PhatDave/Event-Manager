package com.hackathlon.hackathlon.enums;

import lombok.Getter;

@Getter
public enum GithubLanguageGradingEnum {
    JAVA(5),
    PYTHON(3),
    HTML(1);

    private Integer score;
    GithubLanguageGradingEnum(Integer score) {
        this.score = score;
    }
}
