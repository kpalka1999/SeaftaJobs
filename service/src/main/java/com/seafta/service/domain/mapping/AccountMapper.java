package com.seafta.service.domain.mapping;

import com.seafta.service.domain.persistence.model.account.Account;
import com.seafta.service.domain.dto.account.AccountDetails;
import com.seafta.service.domain.dto.account.AccountSnapshot;
import com.seafta.service.domain.dto.account.AccountUpdatedSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "accountId", source = "id")
    AccountSnapshot toAccountSnapshot(Account account);

    @Mapping(target = "accountId", source = "id")
    AccountDetails toAccountDetails(Account account);

    @Mapping(target = "accountId", source = "id")
    AccountUpdatedSnapshot toAccountUpdatedSnapshot(Account account);
}
