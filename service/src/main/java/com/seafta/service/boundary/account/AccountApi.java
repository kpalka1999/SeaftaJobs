package com.seafta.service.boundary.account;

import com.seafta.service.domain.persistence.model.account.Account;
import com.seafta.service.domain.dto.account.AccountDetails;
import com.seafta.service.domain.dto.account.AccountSnapshot;
import com.seafta.service.domain.dto.account.AccountUpdatedSnapshot;
import com.seafta.service.domain.request.account.AccountCreateRequest;
import com.seafta.service.domain.request.account.AccountUpdatePasswordRequest;
import com.seafta.service.domain.request.account.AccountUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/accounts")
public interface AccountApi {

    //todo add permission @PreAuthorize()
    @Operation(summary = "CreateAccount", description = "ACCOUNT_CREATE_POST")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    AccountSnapshot createAccount(@RequestBody @Valid AccountCreateRequest request);

    @Operation(summary = "GetAccount", description = "ACCOUNT_GET")
    @GetMapping(value = "/{accountId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    AccountDetails getAccount(@PathVariable("accountId")UUID accountId);

    @Operation(summary = "GetAccounts", description = "ACCOUNTS_GET")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    List<Account> getAccounts();

    @Operation(summary = "UpdateAccount", description = "ACCOUNT_UPDATE_PUT")
    @PutMapping(value = "/{accountId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    AccountUpdatedSnapshot updateAccount(@PathVariable("accountId") UUID accountId, @RequestBody @Valid AccountUpdateRequest request);

    @Operation(summary = "DeleteAccount", description = "ACCOUNT_DELETE")
    @DeleteMapping(value = "/{accountId}")
    @ResponseStatus(NO_CONTENT)
    void deleteAccount(@PathVariable("accountId") UUID accountId);

    @Operation(summary = "UpdatePasswordAccount", description = "ACCOUNT_PASSWORD_UPDATE_PUT")
    @PutMapping(value = "/{accountId}/password", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    void changePasswordAccount(@PathVariable("accountId") UUID accountId, @RequestBody @Valid AccountUpdatePasswordRequest request);
}
