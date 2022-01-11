package com.seafta.service.boundary.offer

import com.seafta.service.BaseSpecification
import com.seafta.service.domain.dto.offer.OfferDetails
import com.seafta.service.domain.dto.offer.OfferSnapshot
import com.seafta.service.domain.dto.offer.OfferUpdatedSnapshot
import com.seafta.service.domain.persistence.model.account.Account
import com.seafta.service.domain.persistence.model.enums.Level
import com.seafta.service.domain.persistence.model.enums.Location
import com.seafta.service.domain.persistence.model.enums.Technology
import com.seafta.service.domain.persistence.model.offer.Offer
import com.seafta.service.domain.persistence.repository.OfferRepository
import com.seafta.service.domain.request.offer.OfferCreateRequest
import com.seafta.service.domain.request.offer.OfferSearchFilter
import com.seafta.service.domain.request.offer.OfferUpdateRequest
import com.seafta.service.helper.OfferHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Ignore

@Ignore
class OfferControllerTest extends BaseSpecification {

    @Autowired
    private OfferRepository offerRepository

    def cleanup() {
        offerRepository.deleteAll()
    }

    private static final String BASE_URL = "/offers"
    private static final String OFFER_GET_URL = "${BASE_URL}/{offerId}"
    private static final String OFFER_UPDATE_URL = "${BASE_URL}/{offerId}"
    private static final String OFFER_DELETE_URL = "${BASE_URL}/{offerId}"
    private static final String OFFER_GET_BY_ACCOUNT_ID_URL = "${BASE_URL}/account/{accountId}"
    private static final String OFFER_BY_LEVEL_LOCATION_TECHNOLOGY_URL = "${BASE_URL}/{level}/{location}/{technology}"

    def"should create offer"() {
        given:
            OfferCreateRequest request = OfferHelper.buildOfferCreateRequest()
        when:
            ResponseEntity<OfferSnapshot> result = httpPost(BASE_URL, request, OfferSnapshot.class)
        then:
            result.statusCode == HttpStatus.CREATED
            result.body.accountId == request.accountId
            result.body.location == request.location
    }

    def"should get offer by id"() {
        given:
            Offer offer = offerRepository.saveAndFlush(OfferHelper.buildOffer())
        when:
            ResponseEntity<OfferDetails> result = httpGet(OFFER_GET_URL, OfferDetails.class, offer.id)
        then:
            result.statusCode == HttpStatus.OK
            result.body.offerId == offer.id
    }

    def"should update offer"() {
        given:
            Offer offer = offerRepository.saveAndFlush(OfferHelper.buildOffer())
            OfferUpdateRequest request = OfferHelper.buildOfferUpdateRequest()
        when:
            ResponseEntity<OfferUpdatedSnapshot> result = httpPut(OFFER_UPDATE_URL,request, OfferUpdatedSnapshot.class, offer.id)
        then:
            result.statusCode == HttpStatus.OK
            result.body.companyName == request.companyName
    }

    def"should get offers by level, location and technology"() {
        given:
            offerRepository.saveAndFlush(OfferHelper.buildOffer())
            def type = new ParameterizedTypeReference<List<Offer>>(){}
        when:
            ResponseEntity<List<Offer>> result = httpGet(OFFER_BY_LEVEL_LOCATION_TECHNOLOGY_URL, type, Level.JUNIOR, Location.KRAKOW, Technology.JAVA)
        then:
            result.statusCode == HttpStatus.OK
            result.body.size() == 1
    }

    def "should get offers by accountId"() {
        given:
            Offer offer = offerRepository.saveAndFlush(OfferHelper.buildOffer())
            def type = new ParameterizedTypeReference<List<Offer>>(){}
        when:
            ResponseEntity<List<Offer>> result = httpGet(OFFER_GET_BY_ACCOUNT_ID_URL, type, offer.accountId)
        then:
            result.statusCode == HttpStatus.OK
            result.body.size() == 1
    }

    def "should delete offer"() {
        given:
            Offer offer = offerRepository.saveAndFlush(OfferHelper.buildOffer())
        when:
            ResponseEntity<Offer> result = httpDelete(OFFER_DELETE_URL, Offer.class, offer.id)
        then:
            result.statusCode == HttpStatus.NO_CONTENT
            offerRepository.count() == 0
            !offerRepository.existsById(offer.id)



    }
}
