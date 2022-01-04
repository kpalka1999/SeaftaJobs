package com.seafta.service.domain.service;

import com.seafta.service.domain.Account;
import com.seafta.service.domain.dto.account.AccountDetails;
import com.seafta.service.domain.dto.account.AccountSnapshot;
import com.seafta.service.domain.dto.account.AccountUpdatedSnapshot;
import com.seafta.service.domain.request.AccountCreateRequest;
import com.seafta.service.domain.request.AccountUpdatePasswordRequest;
import com.seafta.service.domain.request.AccountUpdateRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountSnapshot createAccount(@NotNull @Valid AccountCreateRequest request);
    AccountDetails getAccount(@NotNull UUID accountId);
    AccountUpdatedSnapshot updateAccount(@NotNull UUID accountId, @NotNull @Valid AccountUpdateRequest request);
    Account getAccountByUsername(@NotNull String username);
    List<Account> getAccounts();
    void deleteAccount(@NotNull UUID accountId);
    void changePassword(@NotNull UUID accountId, @NotNull @Valid AccountUpdatePasswordRequest request);

}
