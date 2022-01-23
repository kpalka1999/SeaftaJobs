package com.seafta.service.domain.persistence.repository;

import com.seafta.service.domain.persistence.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findAllByAccountIdAndCompanyIdOrderByCreated(UUID accountId, UUID companyId);
    List<Message> findAllByAccountIdOrderByCreated(UUID accountId);
    List<Message> findAllByCompanyIdOrderByCreated(UUID companyId);

}
