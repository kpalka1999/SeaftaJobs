package com.seafta.service.domain.service.message;

import com.seafta.service.domain.persistence.model.message.Message;
import com.seafta.service.domain.request.message.MessageRequest;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    void sendMessage(MessageRequest request);
    List<Message> getMessage(UUID accountId, UUID companyId);
    List<Message> getMessageByAccountId(UUID accountId);
    List<Message> getMessageByCompanyId(UUID accountId);
}
