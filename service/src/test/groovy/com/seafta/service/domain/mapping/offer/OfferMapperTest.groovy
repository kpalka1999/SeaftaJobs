package com.seafta.service.domain.mapping.offer

import com.seafta.service.IntegrationTest
import com.seafta.service.comparator.AccountComparator
import com.seafta.service.comparator.OfferComparator
import com.seafta.service.domain.dto.account.AccountDetails
import com.seafta.service.domain.dto.account.AccountSnapshot
import com.seafta.service.domain.dto.account.AccountUpdatedSnapshot
import com.seafta.service.domain.dto.offer.OfferDetails
import com.seafta.service.domain.dto.offer.OfferSnapshot
import com.seafta.service.domain.dto.offer.OfferUpdatedSnapshot
import com.seafta.service.domain.mapping.account.AccountMapper
import com.seafta.service.domain.persistence.model.account.Account
import com.seafta.service.domain.persistence.model.enums.StackLevel
import com.seafta.service.domain.persistence.model.offer.Offer
import com.seafta.service.domain.persistence.model.offer.Stack
import com.seafta.service.domain.persistence.repository.AccountRepository
import com.seafta.service.domain.persistence.repository.OfferRepository
import com.seafta.service.helper.AccountHelper
import com.seafta.service.helper.OfferHelper
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Ignore
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
            Set<Stack> technologyStack = new HashSet<>(Arrays.asList(OfferHelper.buildStack()));
            Offer offer = OfferHelper.buildOffer()
            offer.setTechnologyStack(technologyStack)
        when:
            OfferSnapshot snapshot = mapper.toOfferSnapshot(offer)
        then:
            OfferComparator.compare(offer, snapshot)
            snapshot.technologyStack.size() == 1
    }

    def "should map Offer to OfferDetails"() {
        given:
            Set<Stack> technologyStack = new HashSet<>(Arrays.asList(OfferHelper.buildStack()));
            Offer offer = OfferHelper.buildOffer()
            offer.setTechnologyStack(technologyStack)
        when:
            OfferDetails details = mapper.toOfferDetails(offer)
        then:
            OfferComparator.compare(offer, details)
    }

    def "should map Offer to OfferUpdatedSnapshot"() {
        given:
            Set<Stack> technologyStack = new HashSet<>(Arrays.asList(OfferHelper.buildStack()));
            Offer offer = OfferHelper.buildOffer()
            offer.setTechnologyStack(technologyStack)
        when:
            OfferUpdatedSnapshot snapshot = mapper.toOfferUpdatedSnapshot(offer)
        then:
            OfferComparator.compare(offer, snapshot)
    }
}
