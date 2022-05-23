package com.seafta.service.domain.mapping.offer

import com.seafta.service.IntegrationTest
import com.seafta.service.comparator.OfferComparator
import com.seafta.service.domain.dto.offer.OfferDetails
import com.seafta.service.domain.dto.offer.OfferSnapshot
import com.seafta.service.domain.dto.offer.OfferUpdatedSnapshot
import com.seafta.service.domain.persistence.model.offer.Offer
import com.seafta.service.domain.persistence.repository.OfferRepository
import com.seafta.service.helper.OfferHelper
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification
import spock.lang.Subject

@IntegrationTest
class OfferMapperTest extends Specification {

    @Subject @Autowired OfferMapper mapper

    @Autowired
    private OfferRepository offerRepository

    def cleanup() {
        offerRepository.deleteAll();
    }

    def "should map Offer to OfferSnapshot"() {
        given:
            Offer offer = OfferHelper.buildOffer()
        when:
            OfferSnapshot snapshot = mapper.toOfferSnapshot(offer)
        then:
            OfferComparator.compare(offer, snapshot)
    }

    def "should map Offer to OfferDetails"() {
        given:
            Offer offer = OfferHelper.buildOffer()
        when:
            OfferDetails details = mapper.toOfferDetails(offer)
        then:
            OfferComparator.compare(offer, details)
    }

    def "should map Offer to OfferUpdatedSnapshot"() {
        given:
            Offer offer = OfferHelper.buildOffer()
        when:
            OfferUpdatedSnapshot snapshot = mapper.toOfferUpdatedSnapshot(offer)
        then:
            OfferComparator.compare(offer, snapshot)
    }
}
