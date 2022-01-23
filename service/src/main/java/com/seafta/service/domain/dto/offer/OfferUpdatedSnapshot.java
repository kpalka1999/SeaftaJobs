package com.seafta.service.domain.dto.offer;

import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.Technology;
import com.seafta.service.domain.persistence.model.offer.Stack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferUpdatedSnapshot {

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

//    @NotNull
//    private Set<Stack> technologyStack;
}
