package com.seafta.service.domain.service.offer;

import com.seafta.service.domain.dto.offer.OfferDetails;
import com.seafta.service.domain.dto.offer.OfferSnapshot;
import com.seafta.service.domain.dto.offer.OfferUpdatedSnapshot;
import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.Technology;
import com.seafta.service.domain.persistence.model.offer.Offer;
import com.seafta.service.domain.request.offer.OfferCreateRequest;
import com.seafta.service.domain.request.offer.OfferUpdateRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface OfferService {
    OfferSnapshot createOffer(@NotNull @Valid OfferCreateRequest request);
    OfferDetails getOffer(@NotNull UUID offerId);
    OfferUpdatedSnapshot updateOffer(@NotNull @Valid OfferUpdateRequest request);
    List<Offer> getOffers(Level level, Location location, Technology technology);
    void deleteOffer(@NotNull UUID offerId);
    void changeOffer(@NotNull UUID offerId, @NotNull @Valid OfferUpdateRequest request);
}
