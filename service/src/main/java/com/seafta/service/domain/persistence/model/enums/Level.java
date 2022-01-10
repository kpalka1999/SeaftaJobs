package com.seafta.service.domain.persistence.model.enums;

import lombok.Getter;

public enum Level {

    ALL("All"),
    JUNIOR("Junior"),
    MID("Mid"),
    SENIOR("Senior");

    @Getter
    private final String type;

    Level(String type) {
        this.type = type;
    }
}
