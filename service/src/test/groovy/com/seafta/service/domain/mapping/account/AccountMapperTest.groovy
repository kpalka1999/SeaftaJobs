package com.seafta.service.domain.mapping.account

import com.seafta.service.IntegrationTest
import com.seafta.service.comparator.AccountComparator
import com.seafta.service.domain.dto.account.AccountDetails
import com.seafta.service.domain.dto.account.AccountSnapshot
import com.seafta.service.domain.dto.account.AccountUpdatedSnapshot
import com.seafta.service.domain.persistence.model.account.Account
import com.seafta.service.domain.persistence.repository.AccountRepository
import com.seafta.service.helper.AccountHelper
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification
import spock.lang.Subject

@IntegrationTest
class AccountMapperTest extends Specification {

    @Subject @Autowired AccountMapper mapper

    @Autowired
    private AccountRepository accountRepository

    def cleanup() {
        accountRepository.deleteAll();
    }

    def "should map Account to AccountSnapshot"() {
        given:
            Account account = accountRepository.save(AccountHelper.buildAccount())
        when:
            AccountSnapshot snapshot = mapper.toAccountSnapshot(account)
        then:
        AccountComparator.compare(account, snapshot)
    }

    def "should map Account to AccountDetails"() {
        given:
        Account account = accountRepository.save(AccountHelper.buildAccount())
        when:
        AccountDetails details = mapper.toAccountDetails(account)
        then:
        AccountComparator.compare(account, details)
    }

    def "should map Account to AccountUpdatedSnapshot"() {
        given:
        Account account = accountRepository.save(AccountHelper.buildAccount())
        when:
        AccountUpdatedSnapshot snapshot = mapper.toAccountUpdatedSnapshot(account)
        then:
        AccountComparator.compare(account, snapshot)
    }
}
