package com.seafta.service.domain.persistence.model.enums;

import lombok.Getter;

public enum Technology {
    ALL("All"),
    JS("JS"),
    HTML("HTML"),
    PHP("PHP"),
    RUBY("Ruby"),
    PYTHON("Python"),
    JAVA("Java"),
    NET("Net"),
    SCALA("Scala"),
    C("C"),
    MOBILE("Mobile"),
    TESTING("Testing"),
    DEVOPS("DevOps"),
    ADMIN("Admin"),
    UXUI("UX/UI"),
    PM("PM"),
    GAME("GAME"),
    ANALYTICS("Analytics"),
    SECURITY("Security"),
    DATA("Data"),
    GO("Go"),
    SUPPORT("SUPPORT"),
    ERP("ERP"),
    ARCHITECTURE("Architecture"),
    OTHER("Other");



    @Getter
    private final String type;

    Technology(String type) {
        this.type = type;
    }
}
