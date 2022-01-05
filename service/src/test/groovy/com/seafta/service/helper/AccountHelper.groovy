package com.seafta.service.helper

import com.seafta.service.domain.persistence.model.account.Account
import com.seafta.service.domain.request.account.AccountCreateRequest
import com.seafta.service.domain.request.account.AccountUpdatePasswordRequest
import com.seafta.service.domain.request.account.AccountUpdateRequest

import java.time.Clock
import java.time.OffsetDateTime

class AccountHelper {

    static Account buildAccount(UUID id = UUID.randomUUID(),
                                String email = "test@gmail.com",
                                String password = "password",
                                String firstName = "firstName",
                                String lastName = "lastName",
                                String description = "description",
                                String gitHubUrl = "gitHubUrl",
                                OffsetDateTime created = OffsetDateTime.now(Clock.systemUTC()),
                                OffsetDateTime modified = OffsetDateTime.now(Clock.systemUTC()),
                                OffsetDateTime lastLogout = OffsetDateTime.now(Clock.systemUTC())) {
        Account.builder()
            .id(id)
            .email(email)
            .passwordHash(password)
            .firstName(firstName)
            .lastName(lastName)
            .description(description)
            .gitHubUrl(gitHubUrl)
            .created(created)
            .modified(modified)
            .lastLogout(lastLogout)
            .build()
    }

    static AccountCreateRequest buildAccountCreateRequest(String email = "test@gmail.com",
                                                          String password = "password",
                                                          String firstName = "firstName",
                                                          String lastName = "lastName",
                                                          String description = "description",
                                                          String gitHubUrl = "gitHubUrl") {

        AccountCreateRequest.builder()
            .email(email)
            .password(password)
            .firstName(firstName)
            .lastName(lastName)
            .description(description)
            .gitHubUrl(gitHubUrl)
            .build()
    }

    static AccountUpdatePasswordRequest buildAccountUpdatePasswordRequest(String oldPassword = "password",
                                                                          String newPassword = "newPassword",
                                                                          String repeatPassword = "newPassword") {

        AccountUpdatePasswordRequest.builder()
            .oldPassword(oldPassword)
            .newPassword(newPassword)
            .repeatPassword(repeatPassword)
            .build()
    }

    static AccountUpdateRequest buildAccountUpdateRequest(String firstName = "firstNameUpdated",
                                                          String lastName = "lastNameUpdated",
                                                          String description = "descriptionUpdated",
                                                          String gitHubUrl = "gitHubUrlUpdated") {

        AccountUpdateRequest.builder()
            .firstName(firstName)
            .lastName(lastName)
            .description(description)
            .gitHubUrl(gitHubUrl)
            .build()
    }

}
