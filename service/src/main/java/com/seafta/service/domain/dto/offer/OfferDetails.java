package com.seafta.service.domain.dto.offer;

import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.Technology;
import com.seafta.service.domain.persistence.model.offer.Stack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OfferDetails {

    @NotNull
    private UUID offerId;

    @NotNull
    private UUID accountId;

    @NotNull
    private String companyName;

    @NotNull
    private Level level;

    @NotNull
    private Location location;

    @NotNull
    private Technology technology;

    @NotNull
    private String description;

    private String mainDescription;

    @NotNull
    private Set<Stack> technologyStack;



}
