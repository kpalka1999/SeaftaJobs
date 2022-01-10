package com.seafta.service.domain.mapping.offer;



import com.seafta.service.domain.dto.offer.OfferDetails;
import com.seafta.service.domain.dto.offer.OfferSnapshot;
import com.seafta.service.domain.dto.offer.OfferUpdatedSnapshot;
import com.seafta.service.domain.persistence.model.offer.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    @Mapping(target = "offerId", source = "id")
    OfferSnapshot toOfferSnapshot(Offer offer);

    @Mapping(target = "offerId", source = "id")
    OfferDetails toOfferDetails(Offer offer);

    @Mapping(target = "offerId", source = "id")
    OfferUpdatedSnapshot toOfferUpdatedSnapshot(Offer offer);
}
