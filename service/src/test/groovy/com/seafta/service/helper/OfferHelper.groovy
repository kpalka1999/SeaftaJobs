package com.seafta.service.helper

import com.seafta.service.domain.persistence.model.enums.Level
import com.seafta.service.domain.persistence.model.enums.Location
import com.seafta.service.domain.persistence.model.enums.StackLevel
import com.seafta.service.domain.persistence.model.enums.Technology
import com.seafta.service.domain.persistence.model.offer.Offer
import com.seafta.service.domain.persistence.model.offer.Stack
import com.seafta.service.domain.request.offer.OfferCreateRequest
import com.seafta.service.domain.request.offer.OfferUpdateRequest

import java.time.Clock
import java.time.OffsetDateTime

class OfferHelper {

    static Stack buildStack(String name = "JAVA",
                            StackLevel stackLevel = StackLevel.JUNIOR) {

        Stack.builder()
            .name(name)
            .stackLevel(stackLevel)
            .build()
    }

    static Offer buildOffer(UUID id = UUID.randomUUID(),
                            UUID accountId = UUID.randomUUID(),
                            String companyName = "companyName",
                            Level level = Level.JUNIOR,
                            Location location = Location.KRAKOW,
                            Technology technology = Technology.JAVA,
                            String description = "description",
                            OffsetDateTime created = OffsetDateTime.now(Clock.systemUTC()),
                            Set<Stack> technologyStack = Collections.EMPTY_SET) {

        Offer.builder()
            .id(id)
            .accountId(accountId)
            .companyName(companyName)
            .level(level)
            .location(location)
            .technology(technology)
            .description(description)
            .created(created)
            .technologyStack(technologyStack)
            .build()
    }

    static OfferCreateRequest buildOfferCreateRequest(UUID accountId = UUID.randomUUID(),
                                                      String companyName = "companyName",
                                                      Level level = Level.ALL,
                                                      Location location = Location.ALL,
                                                      Technology technology = Technology.ALL,
                                                      String description = "description",
                                                      OffsetDateTime created = OffsetDateTime.now(Clock.systemUTC()),
                                                      Set<Stack> technologyStack = Collections.EMPTY_SET) {
        OfferCreateRequest.builder()
                .accountId(accountId)
                .companyName(companyName)
                .level(level)
                .location(location)
                .technology(technology)
                .description(description)
                .technologyStack(technologyStack)
                .build()

    }

    static OfferUpdateRequest buildOfferUpdateRequest(UUID accountId = UUID.randomUUID(),
                                                      String companyName = "companyNameUpdated",
                                                      Level level = Level.ALL,
                                                      Location location = Location.ALL,
                                                      Technology technology = Technology.ALL,
                                                      String description = "descriptionUpdated",
                                                      OffsetDateTime created = OffsetDateTime.now(Clock.systemUTC()),
                                                      Set<Stack> technologyStack = Collections.EMPTY_SET) {
        OfferUpdateRequest.builder()
                .accountId(accountId)
                .companyName(companyName)
                .level(level)
                .location(location)
                .technology(technology)
                .description(description)
                .technologyStack(technologyStack)
                .build()
    }
}
