package com.seafta.service;

import com.seafta.service.domain.request.account.AccountCreateRequest;
import com.seafta.service.domain.service.account.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.seafta.*"})
public class SeaftaJobsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeaftaJobsApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AccountService accountService) {
        return args -> {
            accountService.createAccount(AccountCreateRequest.builder()
                    .email("test670@gmail.com")
                    .password("password")
                    .firstName("elo")
                    .lastName("320")
                    .description("description")
                    .gitHubUrl("gitHubUrl")
                    .build());
        };
    }
}

