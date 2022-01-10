package com.seafta.service.domain.service

import com.seafta.service.IntegrationTest
import com.seafta.service.comparator.AccountComparator
import com.seafta.service.domain.dto.account.AccountDetails
import com.seafta.service.domain.dto.account.AccountSnapshot
import com.seafta.service.domain.dto.account.AccountUpdatedSnapshot
import com.seafta.service.domain.persistence.model.account.Account
import com.seafta.service.domain.persistence.repository.AccountRepository
import com.seafta.service.domain.request.account.AccountCreateRequest
import com.seafta.service.domain.request.account.AccountUpdatePasswordRequest
import com.seafta.service.domain.request.account.AccountUpdateRequest
import com.seafta.service.domain.service.account.AccountService
import com.seafta.service.helper.AccountHelper
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Ignore
import spock.lang.Specification

@IntegrationTest
class AccountServiceTest extends Specification {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository

    def cleanup() {
        accountRepository.deleteAll();
    }

    def "should create account"() {
        given:
            AccountCreateRequest request = AccountHelper.buildAccountCreateRequest()
        when:
            AccountSnapshot result = accountService.createAccount(request)
        then:
            accountRepository.count() == 1
            AccountComparator.compare(request, result)
    }

    def "should get account"() {
        given:
            Account account = accountRepository.save(AccountHelper.buildAccount())
        when:
            AccountDetails result = accountService.getAccount(account.getId())
        then:
            AccountComparator.compare(account, result)
    }

    def "should update account"() {
        given:
            Account account = accountRepository.saveAndFlush(AccountHelper.buildAccount())
            AccountUpdateRequest request = AccountHelper.buildAccountUpdateRequest()
        when:
            accountService.updateAccount(account.id, request)
            Account result = accountRepository.getOne(account.id);

        then:
            AccountComparator.compare(result, request)
    }

    def "should update password"() {
        given:
            AccountSnapshot account = accountService.createAccount(AccountHelper.buildAccountCreateRequest())
            AccountUpdatePasswordRequest request = AccountHelper.buildAccountUpdatePasswordRequest()
        when:
            accountService.changePassword(account.getAccountId(), request)
            Account result = accountRepository.getOne(account.getAccountId())
        then:
            result.getPasswordHash() != account.getPasswordHash()
    }

    def "should get accounts"() {
        given:
            Account account = accountRepository.saveAndFlush(AccountHelper.buildAccount())
            Account account1 = accountRepository.saveAndFlush(AccountHelper.buildAccount(UUID.randomUUID(), "test1@gmail.com"))
        when:
            List<Account> accounts = accountService.getAccounts()
        then:
            accountRepository.count() == accounts.size()
    }

    def "should delete account"() {
        given:
            Account account = accountRepository.saveAndFlush(AccountHelper.buildAccount())
            Account account1 = accountRepository.saveAndFlush(AccountHelper.buildAccount(UUID.randomUUID(), "test1@gmail.com"))
        when:
            accountService.deleteAccount(account.getId())
        then:
            accountRepository.count() == 1
            !accountRepository.existsById(account.getId())
            accountRepository.existsById(account1.getId())
    }
}
