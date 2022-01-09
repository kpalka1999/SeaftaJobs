package com.seafta.service.domain.persistence.model.enums;

import lombok.Getter;

public enum Location {
    ALL("All"),
    WARSZAWA("Warszawa"),
    KRAKOW("Kraków"),
    WROCLAW("Wrocław"),
    POZNAN("Poznań"),
    TROJMIASTO("Trójmiasto"),
    SLASK("Śląsk"),
    BIALYSTOK("Białystok"),
    BYDGOSZCZ("Bydgoszcz"),
    BIELSKOBIALA("Bielsko-Biała"),
    CZESTOCHOWA("Częstochowa"),
    KIELCE("Kielce"),
    LUBLIN("Lublin"),
    LODZ("Łódź"),
    OLSZTYN("Olsztyn"),
    OPOLE("Opole"),
    RZESZOW("Rzeszów"),
    SZCZECIN("Szczecin"),
    TORUN("Toruń"),
    ZIELONAGORA("Zielona Góra"),
    OTHER("Other");

    @Getter
    private final String type;

    Location(String type) {
        this.type = type;
    }
}
