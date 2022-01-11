package com.seafta.service.domain.service.offer

import com.seafta.service.IntegrationTest
import com.seafta.service.comparator.AccountComparator
import com.seafta.service.comparator.OfferComparator
import com.seafta.service.domain.dto.account.AccountDetails
import com.seafta.service.domain.dto.account.AccountSnapshot
import com.seafta.service.domain.dto.offer.OfferDetails
import com.seafta.service.domain.dto.offer.OfferSnapshot
import com.seafta.service.domain.persistence.model.account.Account
import com.seafta.service.domain.persistence.model.enums.Level
import com.seafta.service.domain.persistence.model.enums.Location
import com.seafta.service.domain.persistence.model.enums.StackLevel
import com.seafta.service.domain.persistence.model.enums.Technology
import com.seafta.service.domain.persistence.model.offer.Offer
import com.seafta.service.domain.persistence.repository.AccountRepository
import com.seafta.service.domain.persistence.repository.OfferRepository
import com.seafta.service.domain.request.account.AccountCreateRequest
import com.seafta.service.domain.request.account.AccountUpdatePasswordRequest
import com.seafta.service.domain.request.account.AccountUpdateRequest
import com.seafta.service.domain.request.offer.OfferCreateRequest
import com.seafta.service.domain.request.offer.OfferSearchFilter
import com.seafta.service.domain.request.offer.OfferUpdateRequest
import com.seafta.service.domain.service.account.AccountService
import com.seafta.service.helper.AccountHelper
import com.seafta.service.helper.OfferHelper
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@IntegrationTest
class OfferServiceTest extends Specification {

    @Autowired
    private OfferService offerService

    @Autowired
    private OfferRepository offerRepository

    def cleanup() {
        offerRepository.deleteAll()
    }

    def "should create offer"() {
        given:
            OfferCreateRequest request = OfferHelper.buildOfferCreateRequest()
        when:
            OfferSnapshot result = offerService.createOffer(request)
        then:
            offerRepository.count() == 1
            OfferComparator.compare(request, result)
    }

    def "should get offer"() {
        given:
            Offer offer = offerRepository.saveAndFlush(OfferHelper.buildOffer())
        when:
            OfferDetails result = offerService.getOffer(offer.id)
        then:
            OfferComparator.compare(offer, result)
    }

    def "should update offer"() {
        given:
            Offer offer = offerRepository.saveAndFlush(OfferHelper.buildOffer())
            OfferUpdateRequest request = OfferHelper.buildOfferUpdateRequest()
        when:
            offerService.updateOffer(offer.id, request)
            Offer result = offerRepository.getOne(offer.id)
        then:
            OfferComparator.compare(result, request)

    }

    def "should get offers by level, location and technology"() {
        given:
            Offer offer = offerRepository.saveAndFlush(OfferHelper.buildOffer())
        when:
            List<Offer> offers = offerService.getOffers(Level.JUNIOR, Location.KRAKOW, Technology.JAVA)
        then:
            offers.size() == 1
            offers[0].getLevel() == Level.JUNIOR

    }

    def "should get offers"() {
        given:
            Offer offer = offerRepository.saveAndFlush(OfferHelper.buildOffer())
        when:
            List<Offer> offers = offerService.getOffersByAccount(offer.accountId)
        then:
            offers.size() == 1
            offers[0].getAccountId() == offer.getAccountId()
    }

    def "should delete offer"() {
        given:
            Offer offer = offerRepository.saveAndFlush(OfferHelper.buildOffer())
        when:
            offerService.deleteOffer(offer.id)
        then:
            offerRepository.count() == 0
            !offerRepository.existsById(offer.id)
    }
}
