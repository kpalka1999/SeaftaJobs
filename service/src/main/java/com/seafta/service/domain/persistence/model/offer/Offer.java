package com.seafta.service.domain.persistence.model.offer;


import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.StackLevel;
import com.seafta.service.domain.persistence.model.enums.Technology;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "company_name")
    private String companyName;

    private Level level;

    private Location location;

    private Technology technology;

    private String description;

    @OneToMany(mappedBy = "offer",
               cascade = CascadeType.ALL,
               fetch = FetchType.EAGER)
    private Set<Stack> technologyStack;

}
