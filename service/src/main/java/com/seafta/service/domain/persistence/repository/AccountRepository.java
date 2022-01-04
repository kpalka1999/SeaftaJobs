package com.seafta.service.domain.persistence.repository;

import com.seafta.service.domain.persistence.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByEmail(String email);
}
