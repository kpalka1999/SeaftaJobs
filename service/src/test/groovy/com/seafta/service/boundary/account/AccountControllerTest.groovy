package com.seafta.service.boundary.account

import com.seafta.service.BaseSpecification
import com.seafta.service.domain.dto.account.AccountDetails
import com.seafta.service.domain.dto.account.AccountSnapshot
import com.seafta.service.domain.dto.account.AccountUpdatedSnapshot
import com.seafta.service.domain.persistence.model.account.Account
import com.seafta.service.domain.persistence.repository.AccountRepository
import com.seafta.service.domain.request.account.AccountCreateRequest
import com.seafta.service.domain.request.account.AccountUpdatePasswordRequest
import com.seafta.service.domain.request.account.AccountUpdateRequest
import com.seafta.service.helper.AccountHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Ignore

@Ignore
//todo Create test profile with disabled security
class AccountControllerTest extends BaseSpecification {

    @Autowired
    private AccountRepository accountRepository

    @Autowired
    private PasswordEncoder passwordEncoder

    def cleanup() {
        accountRepository.deleteAll()
    }

    def "should create account"() {
        given:
            AccountCreateRequest request = AccountHelper.buildAccountCreateRequest()
        when:
            ResponseEntity<AccountSnapshot> result = httpPost("/accounts", request, AccountSnapshot.class)
        then:
            result.statusCode == HttpStatus.CREATED
            result.body.email == request.email
    }

    def "should return 400 status by entering an invalid email to AccountCreateRequest"() {
        given:
            AccountCreateRequest request = AccountHelper.buildAccountCreateRequest()
            request.setEmail("test")
        when:
            ResponseEntity<AccountSnapshot> result = httpPost("/accounts", request, AccountSnapshot.class)
        then:
            result.statusCode == HttpStatus.BAD_REQUEST
    }

    def "should return 400 status by entering an empty password to AccountCreateRequest"() {
        given:
            AccountCreateRequest request = AccountHelper.buildAccountCreateRequest()
            request.setPassword(null)
        when:
            ResponseEntity<AccountSnapshot> result = httpPost("/accounts", request, AccountSnapshot.class)
        then:
            result.statusCode == HttpStatus.BAD_REQUEST
    }

    def "should get account by id"() {
        given:
            Account account = accountRepository.saveAndFlush(AccountHelper.buildAccount())
        when:
            ResponseEntity<AccountDetails> result = httpGet("/accounts/{accountId}", AccountDetails.class, account.id)
        then:
            result.statusCode ==HttpStatus.OK
            result.body.accountId == account.id
    }

    def "should update account"() {
        given:
            Account account = accountRepository.saveAndFlush(AccountHelper.buildAccount())
            AccountUpdateRequest request = AccountHelper.buildAccountUpdateRequest()
            request.setFirstName("firstNameUpdated")
        when:
            ResponseEntity<AccountUpdatedSnapshot> result = httpPut("/accounts/{accountId}", request, AccountUpdatedSnapshot.class, account.id)
        then:
        result.statusCode == HttpStatus.OK
        result.body.firstName == request.firstName
    }

    def "should delete account"() {
        given:
            Account account = accountRepository.saveAndFlush(AccountHelper.buildAccount())
            int numberOfAccounts = (int) accountRepository.count()
        when:
            ResponseEntity<Account> result = httpDelete("/accounts/{accountId}", Account.class, account.id)
        then:
            accountRepository.count() == 0
            numberOfAccounts == 1
            result.statusCode == HttpStatus.NO_CONTENT
    }

    def "should change password"() {
        given:
            Account account = accountRepository.saveAndFlush(AccountHelper.buildAccount(UUID.randomUUID(),"test@gmail.com", passwordEncoder.encode("password")))
            AccountUpdatePasswordRequest request = AccountHelper.buildAccountUpdatePasswordRequest()
        when:
            ResponseEntity<Void> result = httpPut("/accounts/{accountId}/password", request, Void.class, account.id)
        then:
            result.statusCode == HttpStatus.OK


    }

    def "should return 400 status by entering an invalid new password to AccountUpdatePasswordRequest"() {
        given:
            Account account = accountRepository.saveAndFlush(AccountHelper.buildAccount())
            AccountUpdatePasswordRequest request = AccountHelper.buildAccountUpdatePasswordRequest()
            request.setNewPassword(null)
        when:
            ResponseEntity<Void> httpResult = httpPut("/accounts/{accountId}/password", request, Void.class, account.id)
        then:
            httpResult.statusCode == HttpStatus.BAD_REQUEST
    }

    def "should return 400 status by entering an invalid old password to AccountUpdatePasswordRequest"() {
        given:
            Account account = accountRepository.saveAndFlush(AccountHelper.buildAccount())
            AccountUpdatePasswordRequest request = AccountHelper.buildAccountUpdatePasswordRequest()
            request.setOldPassword(null)
        when:
            ResponseEntity<Void> httpResult = httpPut("/accounts/{accountId}/password", request, Void.class, account.id)
        then:
            httpResult.statusCode == HttpStatus.BAD_REQUEST
    }

    def "should return 400 status by entering an invalid repeat password to AccountUpdatePasswordRequest"() {
        given:
            Account account = accountRepository.saveAndFlush(AccountHelper.buildAccount())
            AccountUpdatePasswordRequest request = AccountHelper.buildAccountUpdatePasswordRequest()
            request.setRepeatPassword(null)
        when:
            ResponseEntity<Void> httpResult = httpPut("/accounts/{accountId}/password", request, Void.class, account.id)
        then:
            httpResult.statusCode == HttpStatus.BAD_REQUEST
    }
}
