package com.seafta.service.boundary.offer;

import com.seafta.service.domain.dto.offer.OfferDetails;
import com.seafta.service.domain.dto.offer.OfferSnapshot;
import com.seafta.service.domain.dto.offer.OfferUpdatedSnapshot;
import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.Technology;
import com.seafta.service.domain.persistence.model.offer.Offer;
import com.seafta.service.domain.request.offer.OfferCreateRequest;
import com.seafta.service.domain.request.offer.OfferUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/offers")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public interface OfferApi {

    @Operation(summary = "CreateOffer", description = "OFFER_CREATE_POST")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    OfferSnapshot createOffer(@RequestBody @Valid OfferCreateRequest request);

    @Operation(summary = "GetOffer", description = "OFFER_GET")
    @GetMapping(value = "/{offerId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    OfferDetails getOffer(@PathVariable("offerId") UUID offerId);

    @Operation(summary = "UpdateOffer", description = "OFFER_UPDATE_PUT")
    @PutMapping(value = "/{offerId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    OfferUpdatedSnapshot updateOffer(@PathVariable("offerId") UUID offerId, @RequestBody @Valid OfferUpdateRequest request);

    @Operation(summary = "GetOffers", description = "OFFERS_GET")
    @GetMapping(value = "{level}/{location}/{technology}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    List<Offer> getOffers(@PathVariable("level") Level level, @PathVariable("location") Location location, @PathVariable("technology")Technology technology);

    @Operation(summary = "GetOffersByAccount", description = "OFFERS_ACCOUNT_GET")
    @GetMapping(value = "/account/{accountId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    List<Offer> getOffersByAccount(@PathVariable("accountId")UUID accountId);


    @Operation(summary = "DeleteOffer", description = "OFFER_DELETE")
    @DeleteMapping(value = "/{offerId}")
    @ResponseStatus(NO_CONTENT)
    void deleteOffer(@PathVariable("offerId") UUID offerId);
}
