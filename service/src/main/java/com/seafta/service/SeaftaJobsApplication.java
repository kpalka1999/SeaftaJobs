package com.seafta.service;

import com.seafta.service.domain.dto.account.AccountSnapshot;
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
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
             AccountSnapshot accountUser = accountService.createAccount(AccountCreateRequest.builder()
                    .email("test420@gmail.com")
                    .password("password")
                    .firstName("elo")
                    .lastName("320")
                    .description("description")
                    .gitHubUrl("gitHubUrl")
                    .isUserAccount(true)
                    .build());

            AccountSnapshot accountCompany = accountService.createAccount(AccountCreateRequest.builder()
                    .email("test69@gmail.com")
                    .password("password")
                    .firstName("elo")
                    .lastName("320")
                    .description("description")
                    .gitHubUrl("gitHubUrl")
                    .isUserAccount(false)
                    .build());

            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(accountCompany.getAccountId())
                    .companyName("Accenture Sp. z o.o.")
                    .level(Level.JUNIOR)
                    .location(Location.KRAKOW)
                    .technology(Technology.JAVA)
                    .description("description")
                    .mainDescription("Junior Java Developer")
//                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());
            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(accountCompany.getAccountId())
                    .companyName("Sollers Consulting Sp. z o.o")
                    .level(Level.JUNIOR)
                    .location(Location.KRAKOW)
                    .technology(Technology.JAVA)
                    .description("descriptddion")
                    .mainDescription("Java Developer Salesforce")
//                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());
            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(accountCompany.getAccountId())
                    .companyName("CodeAlly")
                    .level(Level.JUNIOR)
                    .location(Location.KRAKOW)
                    .technology(Technology.JAVA)
                    .description("desffffffffffftddion")
                    .mainDescription("Junior Developer Backend")
//                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());
            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(accountCompany.getAccountId())
                    .companyName("Nokia")
                    .level(Level.JUNIOR)
                    .location(Location.KRAKOW)
                    .technology(Technology.JAVA)
                    .description("desffffffffffftddion")
                    .mainDescription("Java Developer")
//                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());

            offerService.createOffer(OfferCreateRequest.builder()
                    .accountId(accountCompany.getAccountId())
                    .companyName("Nokia Zjebs")
                    .level(Level.JUNIOR)
                    .location(Location.KRAKOW)
                    .technology(Technology.JAVA)
                    .description("Nokia is committed to innovation and technology leadership across mobile, fixed and cloud networks. Your career here will have a positive impact on people’s lives and will help us build the capabilities needed for a more productive, sustainable, and inclusive world. We challenge ourselves to create an inclusive way of working where we are open to new ideas, empowered to take risks and fearless to bring our authentic selves to work.\n" +
                            " \n" +
                            "The team you'll be part of\n" +
                            "\n" +
                            "Our Business Group is a leader in wireless mobility networks and associated services. With more than 3.500 patent families essential for 5G we have a strong 5G portfolio and are a front runner in open and virtualized radio access networks (O-RAN and vRAN).\n" +
                            "The Mobile Networks Customer Documentation team creates hardware and software descriptions and instructions for the use of the mobile operator personnel. Our goal is to bring the end-user documentation into the visual online world.")
                    .mainDescription("Junior Plereza Developer")
//                    .technologyStack(Collections.asSet(Stack.buildStack("JAVA", StackLevel.JUNIOR)))
                    .build());
        };
    }
}

