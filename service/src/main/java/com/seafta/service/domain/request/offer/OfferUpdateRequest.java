package com.seafta.service.domain.request.offer;

import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.Technology;
import com.seafta.service.domain.persistence.model.offer.Stack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OfferUpdateRequest {

    @NotNull
    private UUID accountId;

    @NotBlank
    private String companyName;

    @NotNull
    private Level level;

    @NotNull
    private Location location;

    @NotNull
    private Technology technology;

    @NotBlank
    private String mainDescription;

    @NotBlank
    private String description;

    @NotNull
    private Set<Stack> technologyStack;
}
