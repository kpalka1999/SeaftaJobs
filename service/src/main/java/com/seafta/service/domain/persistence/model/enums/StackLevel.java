package com.seafta.service.domain.persistence.model.enums;

import lombok.Getter;

public enum StackLevel {

    NICE("Nice to have"),
    JUNIOR("Junior"),
    REGULAR("Regular"),
    ADVANCED("Advanced"),
    MASTER("Master");

    @Getter
    private final String type;

    StackLevel(String type) {
        this.type = type;
    }
}
