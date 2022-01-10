package com.seafta.service.comparator

import com.seafta.service.domain.dto.account.AccountDetails
import com.seafta.service.domain.dto.account.AccountSnapshot
import com.seafta.service.domain.dto.account.AccountUpdatedSnapshot
import com.seafta.service.domain.persistence.model.account.Account
import com.seafta.service.domain.request.account.AccountCreateRequest
import com.seafta.service.domain.request.account.AccountUpdateRequest

class AccountComparator {

    static boolean compare(Account account, AccountSnapshot snapshot) {
        assert account.id == snapshot.accountId
        assert account.email == snapshot.email
        true
    }

    static boolean compare(Account account, AccountDetails details) {
        assert account.id == details.accountId
        assert account.email == details.email
        assert account.firstName == details.firstName
        assert account.lastName == details.lastName
        assert account.description == details.description
        assert account.gitHubUrl == details.gitHubUrl
        true
    }

    static boolean compare(Account account, AccountUpdatedSnapshot snapshot) {
        assert account.id == snapshot.accountId
        assert account.email == snapshot.email
        assert account.firstName == snapshot.firstName
        assert account.lastName == snapshot.lastName
        assert account.description == snapshot.description
        assert account.gitHubUrl == snapshot.gitHubUrl
        true
    }

    static boolean compare(AccountCreateRequest request, AccountSnapshot snapshot) {
        assert request.email == snapshot.email
        true
    }

    static boolean compare(AccountUpdateRequest request, AccountUpdatedSnapshot snapshot) {
        assert request.firstName == snapshot.firstName
        assert request.lastName == snapshot.lastName
        assert request.description == snapshot.description
        assert request.gitHubUrl == snapshot.gitHubUrl
        true
    }

    static boolean compare(Account account, AccountUpdateRequest request) {
        assert request.firstName == account.firstName
        assert request.lastName == account.lastName
        assert request.description == account.description
        assert request.gitHubUrl == account.gitHubUrl
        true
    }
}
