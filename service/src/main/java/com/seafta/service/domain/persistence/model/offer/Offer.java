package com.seafta.service.domain.persistence.model.offer;


import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.Technology;
import com.seafta.service.domain.request.offer.OfferCreateRequest;
import com.seafta.service.domain.request.offer.OfferUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "company_name")
    private String companyName;

    private Level level;

    private Location location;

    private Technology technology;

    private String mainDescription;

    @Column(length = 10000)
    private String description;

    @NotNull
    private OffsetDateTime created;

//    @OneToMany(mappedBy = "offer",
//               cascade = CascadeType.ALL,
//               fetch = FetchType.EAGER)
//    private transient Set<Stack> technologyStack;

    public static Offer buildOffer(@NotNull @Valid OfferCreateRequest request) {
//        Set<Stack> stacks = request.getTechnologyStack();
        Offer offer = Offer.builder()
                .accountId(request.getAccountId())
                .companyName(request.getCompanyName())
                .level(request.getLevel())
                .location(request.getLocation())
                .technology(request.getTechnology())
                .mainDescription(request.getMainDescription())
                .description(request.getDescription())
                .created(OffsetDateTime.now(Clock.systemUTC()))
//                .technologyStack(stacks)
                .build();
//        stacks.forEach(stack -> stack.setOffer(offer));
        return offer;
    }

    public Offer editOffer(@NotNull @Valid OfferUpdateRequest request) {
        this.companyName = request.getCompanyName();
        this.level = request.getLevel();
        this.location = request.getLocation();
        this.technology = request.getTechnology();
        this.description = request.getDescription();
        this.mainDescription = request.getMainDescription();
        return this;
    }

    @PrePersist
    private void onPrePersist() {
        created = OffsetDateTime.now(ZoneOffset.UTC);
    }

}
