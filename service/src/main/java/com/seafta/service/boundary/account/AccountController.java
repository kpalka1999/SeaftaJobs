package com.seafta.service.boundary.account;

import com.seafta.service.domain.persistence.model.account.Account;
import com.seafta.service.domain.dto.account.AccountDetails;
import com.seafta.service.domain.dto.account.AccountSnapshot;
import com.seafta.service.domain.dto.account.AccountUpdatedSnapshot;
import com.seafta.service.domain.request.account.AccountCreateRequest;
import com.seafta.service.domain.request.account.AccountUpdatePasswordRequest;
import com.seafta.service.domain.request.account.AccountUpdateRequest;
import com.seafta.service.domain.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owasp.security.logging.SecurityMarkers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi{

    private final AccountService accountService;

    @Override
    public AccountSnapshot createAccount(@NotNull @Valid AccountCreateRequest request) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Account Controller: Creating new account {request: {}}", request);
        AccountSnapshot result = accountService.createAccount(request);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Account Controller: Created new account {result: {}}", result);
        return result;
    }

    @Override
    public AccountDetails getAccount(@NotNull UUID accountId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Account Controller: Getting account {accountId: {}}", accountId);
        AccountDetails result = accountService.getAccount(accountId);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Account Controller: Got account {accountId: {}}", accountId);
        return result;
    }

    @Override
    public List<Account> getAccounts() {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Account Controller: Getting account");
        List<Account> result = accountService.getAccounts();
        log.debug(SecurityMarkers.CONFIDENTIAL, "Account Controller: Got accounts");
        return result;
    }

    @Override
    public AccountUpdatedSnapshot updateAccount(@NotNull UUID accountId, @NotNull @Valid AccountUpdateRequest request) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Account Controller: Updating account {accountId: {}, request: {}}", accountId, request);
        AccountUpdatedSnapshot result = accountService.updateAccount(accountId, request);
        log.trace(SecurityMarkers.CONFIDENTIAL, "Account Controller: Updated account {accountId: {}, result: {}}", accountId, result);
        return result;
    }

    @Override
    public void deleteAccount(@NotNull UUID accountId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Account Controller: Deleting account {accountId: {}}", accountId);
        accountService.deleteAccount(accountId);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Account Controller: Deleted account {accountId: {}}", accountId);
    }

    @Override
    public void changePasswordAccount(@NotNull UUID accountId, @NotNull @Valid AccountUpdatePasswordRequest request) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Account Controller: Changing password {accountId: {}, request: {}}", accountId, request);
        accountService.changePassword(accountId, request);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Account Controller: Changed password {accountId: {}", accountId);
    }

    public Account getLoggedAccountDetails() {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Account Controller: Getting details about logged user");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Account result = accountService.getAccountByUsername(name);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Account Controller: Got details about logged user {result: {}}", result);
        return result;
    }
}
