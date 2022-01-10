package com.seafta.service;

import com.seafta.service.domain.persistence.model.account.Account;
import com.seafta.service.domain.request.account.AccountCreateRequest;
import com.seafta.service.domain.service.account.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.seafta.*"})
public class SeaftaJobsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeaftaJobsApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(AccountService accountService) {
//        return args -> {
//            accountService.createAccount(AccountCreateRequest.builder()
//                    .email("test615@gmail.com")
//                    .password("password")
//                    .firstName("elo")
//                    .lastName("320")
//                    .description("description")
//                    .gitHubUrl("gitHubUrl")
//                    .build());
//        };
//    }
}

