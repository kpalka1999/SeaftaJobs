package com.seafta.service.boundary.offer;

import com.seafta.service.domain.dto.offer.OfferDetails;
import com.seafta.service.domain.dto.offer.OfferSnapshot;
import com.seafta.service.domain.dto.offer.OfferUpdatedSnapshot;
import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.Technology;
import com.seafta.service.domain.persistence.model.offer.Offer;
import com.seafta.service.domain.request.offer.OfferCreateRequest;
import com.seafta.service.domain.request.offer.OfferSearchFilter;
import com.seafta.service.domain.request.offer.OfferUpdateRequest;
import com.seafta.service.domain.service.offer.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owasp.security.logging.SecurityMarkers;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class OfferController implements OfferApi {

    private final OfferService offerService;
    @Override
    public OfferSnapshot createOffer(@NotNull @Valid OfferCreateRequest request) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer controller: Creating offer {request: {}}", request);
        OfferSnapshot result = offerService.createOffer(request);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Offer controller: Created offer {result: {}}", result);
        return result;
    }

    @Override
    public OfferDetails getOffer(@NotNull UUID offerId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer controller: Getting offer {offerId: {}}", offerId);
        OfferDetails result = offerService.getOffer(offerId);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Offer controller: Got offer {offerId: {}, result: {}}", offerId, result);
        return result;
    }

    @Override
    public OfferUpdatedSnapshot updateOffer(@NotNull UUID offerId, @NotNull @Valid OfferUpdateRequest request) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer controller: Updating offer {request: {}}", request);
        OfferUpdatedSnapshot result = offerService.updateOffer(offerId, request);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Offer controller: Updated offer {result: {}}", result);
        return result;
    }

    @Override
    public List<Offer> getOffers(Level level, Location location, Technology technology) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer controller: Getting offers by {level: {}, location: {}, technology: {}}", level, location, technology);
        List<Offer> result = offerService.getOffers(level, location, technology);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Offer controller: Got offers {result: {}}", result);
        return result;
    }

    @Override
    public List<Offer> getOffersByAccount(@NotNull UUID accountId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer controller: Getting offers by {accountId: {}}", accountId);
        List<Offer> result = offerService.getOffersByAccount(accountId);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Offer controller: Got offers {result: {}}", result);
        return result;
    }

    @Override
    public void deleteOffer(@NotNull UUID offerId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer controller: Deleting offer {offerId: {}}", offerId);
        offerService.deleteOffer(offerId);
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer controller: Deleted offer {offerId: {}}", offerId);
    }
}
