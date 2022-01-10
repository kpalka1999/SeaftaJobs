package com.seafta.service.comparator

import com.seafta.service.domain.dto.offer.OfferDetails
import com.seafta.service.domain.dto.offer.OfferSnapshot
import com.seafta.service.domain.dto.offer.OfferUpdatedSnapshot
import com.seafta.service.domain.persistence.model.offer.Offer
import com.seafta.service.domain.request.offer.OfferCreateRequest
import com.seafta.service.domain.request.offer.OfferUpdateRequest

class OfferComparator {

    static boolean compare(Offer offer, OfferSnapshot snapshot) {
        assert offer.id == snapshot.offerId
        assert offer.companyName == snapshot.companyName
        assert offer.level == snapshot.level
        assert offer.location == snapshot.location
        assert offer.technology == snapshot.technology
        assert offer.description == snapshot.description
        assert offer.technologyStack == snapshot.technologyStack
        true
    }

    static boolean compare(Offer offer, OfferDetails details) {
        assert offer.id == details.offerId
        assert offer.companyName == details.companyName
        assert offer.level == details.level
        assert offer.location == details.location
        assert offer.technology == details.technology
        assert offer.description == details.description
        assert offer.technologyStack == details.technologyStack
        true
    }

    static boolean compare(Offer offer, OfferUpdatedSnapshot snapshot) {
        assert offer.id == snapshot.offerId
        assert offer.companyName == snapshot.companyName
        assert offer.level == snapshot.level
        assert offer.location == snapshot.location
        assert offer.technology == snapshot.technology
        assert offer.description == snapshot.description
        assert offer.technologyStack == snapshot.technologyStack
        true
    }

    static boolean compare(OfferCreateRequest request, OfferSnapshot snapshot) {
        assert request.companyName == snapshot.companyName
        assert request.level == snapshot.level
        assert request.location == snapshot.location
        assert request.technology == snapshot.technology
        assert request.description == snapshot.description
        assert request.technologyStack == snapshot.technologyStack
        true
    }

    static boolean compare(OfferUpdateRequest request, OfferUpdatedSnapshot snapshot) {
        assert request.companyName == snapshot.companyName
        assert request.level == snapshot.level
        assert request.location == snapshot.location
        assert request.technology == snapshot.technology
        assert request.description == snapshot.description
        assert request.technologyStack == snapshot.technologyStack
        true
    }

    static boolean compare(Offer offer, Offer result) {
        assert offer.id == result.id
        assert offer.companyName == result.companyName
        assert offer.level == result.level
        assert offer.location == result.location
        assert offer.technology == result.technology
        assert offer.description == result.description
        assert offer.technologyStack == result.technologyStack
        true
    }

    static boolean compare(Offer offer, OfferUpdateRequest result) {
        assert offer.companyName == result.companyName
        assert offer.level == result.level
        assert offer.location == result.location
        assert offer.technology == result.technology
        assert offer.description == result.description
        assert offer.technologyStack == result.technologyStack
        true
    }


}
