package com.seafta.service.domain.persistence.repository;

import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.Technology;
import com.seafta.service.domain.persistence.model.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    Offer findByAccount_Email(String email);
    List<Offer> findAllByLevelAndLocationAndTechnology(Level level, Location location, Technology technology);
    List<Offer> findAllByAccount_Id(UUID accountId);
}
