package com.seafta.service.domain.persistence.model.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seafta.service.domain.persistence.model.account.AccountRole;
import com.seafta.service.domain.persistence.model.enums.StackLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "offer")
@Entity
public class Stack implements Serializable {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "stack_level")
    private StackLevel stackLevel;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    @JsonIgnore
    private Offer offer;


    public static Stack buildStack(String name, StackLevel level) {
        return Stack.builder()
                .name(name)
                .stackLevel(level)
                .build();
    }

    public String getStackLevel() {
        return this.stackLevel.toString();
    }
}
