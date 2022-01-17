package com.seafta.service;

import com.seafta.service.domain.persistence.model.account.Account;
import com.seafta.service.domain.persistence.model.enums.Level;
import com.seafta.service.domain.persistence.model.enums.Location;
import com.seafta.service.domain.persistence.model.enums.StackLevel;
import com.seafta.service.domain.persistence.model.enums.Technology;
import com.seafta.service.domain.persistence.model.offer.Stack;
import com.seafta.service.domain.request.account.AccountCreateRequest;
import com.seafta.service.domain.request.offer.OfferCreateRequest;
import com.seafta.service.domain.service.account.AccountService;
import com.seafta.service.domain.service.offer.OfferService;
import org.mapstruct.ap.internal.util.Collections;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@SpringBootApplication
@ComponentScan(basePackages = {"com.seafta.*"})
public class SeaftaJobsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeaftaJobsApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AccountService accountService, OfferService offerService) {
        return args -> {
            accountService.createAccount(AccountCreateRequest.builder()
                    .email("test420@gmail.com")
                    .password("password")
                    .firstName("elo")
                    .lastName("320")
                    .description("description")
                    .gitHubUrl("gitHubUrl")
                    .build());

            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(UUID.randomUUID())
                    .companyName("Accenture Sp. z o.o.")
                    .level(Level.JUNIOR)
                    .location(Location.KRAKOW)
                    .technology(Technology.JAVA)
                    .description("description")
                    .mainDescription("Junior Java Developer")
                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());
            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(UUID.randomUUID())
                    .companyName("Sollers Consulting Sp. z o.o")
                    .level(Level.JUNIOR)
                    .location(Location.KRAKOW)
                    .technology(Technology.JAVA)
                    .description("descriptddion")
                    .mainDescription("Java Developer Salesforce")
                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());
            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(UUID.randomUUID())
                    .companyName("CodeAlly")
                    .level(Level.JUNIOR)
                    .location(Location.KRAKOW)
                    .technology(Technology.JAVA)
                    .description("desffffffffffftddion")
                    .mainDescription("Junior Developer Backend")
                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());
            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(UUID.randomUUID())
                    .companyName("Nokia")
                    .level(Level.JUNIOR)
                    .location(Location.KRAKOW)
                    .technology(Technology.JAVA)
                    .description("desffffffffffftddion")
                    .mainDescription("Java Developer")
                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());
            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(UUID.randomUUID())
                    .companyName("Nokia")
                    .level(Level.JUNIOR)
                    .location(Location.KRAKOW)
                    .technology(Technology.JAVA)
                    .description("desffffffffffftddion")
                    .mainDescription("Java Developer")
                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());
            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(UUID.randomUUID())
                    .companyName("Nokia")
                    .level(Level.JUNIOR)
                    .location(Location.WARSZAWA)
                    .technology(Technology.PHP)
                    .description("desffffffffffftddion")
                    .mainDescription("Java Developer")
                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());
        };
    }
}

