package com.seafta.service.domain.service.account;

import com.seafta.service.domain.persistence.model.account.Account;
import com.seafta.service.domain.dto.account.AccountDetails;
import com.seafta.service.domain.dto.account.AccountSnapshot;
import com.seafta.service.domain.dto.account.AccountUpdatedSnapshot;
import com.seafta.service.domain.request.account.AccountCreateRequest;
import com.seafta.service.domain.request.account.AccountUpdatePasswordRequest;
import com.seafta.service.domain.request.account.AccountUpdateRequest;

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
