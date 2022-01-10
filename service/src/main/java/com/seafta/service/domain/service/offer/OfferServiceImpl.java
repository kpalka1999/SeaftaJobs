package com.seafta.service.domain.service.offer;

import com.seafta.service.domain.dto.offer.OfferDetails;
import com.seafta.service.domain.dto.offer.OfferSnapshot;
import com.seafta.service.domain.dto.offer.OfferUpdatedSnapshot;
import com.seafta.service.domain.mapping.offer.OfferMapper;
import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.Technology;
import com.seafta.service.domain.persistence.model.offer.Offer;
import com.seafta.service.domain.persistence.repository.OfferRepository;
import com.seafta.service.domain.request.offer.OfferCreateRequest;
import com.seafta.service.domain.request.offer.OfferUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owasp.security.logging.SecurityMarkers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    @Override
    public OfferSnapshot createOffer(@NotNull @Valid OfferCreateRequest request) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer service: Creating offer {request: {}}", request);
        Offer offer = offerRepository.save(Offer.buildOffer(request));
        OfferSnapshot result = offerMapper.toOfferSnapshot(offer);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Offer service: Created offer {result: {}}", result);
        return result;
    }

    @Override
    public OfferDetails getOffer(@NotNull UUID offerId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer service: Getting offer {offerId: {}}", offerId);
        Offer offer = offerRepository.getOne(offerId);
        OfferDetails result = offerMapper.toOfferDetails(offer);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Offer service: Got offer {offerId: {}}", offerId);
        return result;
    }

    @Override
    public OfferUpdatedSnapshot updateOffer(@NotNull UUID offerId, @NotNull @Valid  OfferUpdateRequest request) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer service: Updating offer {request: {}}", request);
        Offer offer = offerRepository.getOne(offerId);
        OfferUpdatedSnapshot result = offerMapper.toOfferUpdatedSnapshot(offer.editOffer(request));
        offerRepository.save(offer);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Offer service: Updated offer {result: {}}", result);
        return result;
    }

    @Override
    public List<Offer> getOffers(@NotNull Level level, @NotNull Location location, @NotNull Technology technology) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer service: Getting offers by {level: {}, location: {}, technology: {}}", level, location, technology);
        List<Offer> result = offerRepository.findAllByLevelAndLocationAndTechnology(level, location, technology);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Offer service: Got offers {result: {}}", result);
        return result;
    }

    @Override
    public List<Offer> getOffersByAccount(@NotNull UUID accountId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer service: Getting offers by {accountId: {}}", accountId);
        List<Offer> result = offerRepository.findAllByAccount_Id(accountId);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Offer service: Got offers {result: {}}", result);
        return result;
    }

    @Override
    public void deleteOffer(@NotNull UUID offerId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer service: Deleting offer {offerId: {}}", offerId);
        offerRepository.deleteById(offerId);
        log.trace(SecurityMarkers.CONFIDENTIAL, "Offer service: Deleted offer {offerId: {}}", offerId);
    }
}
