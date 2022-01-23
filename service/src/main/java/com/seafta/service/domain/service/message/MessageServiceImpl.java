package com.seafta.service.domain.service.message;

import com.seafta.service.domain.persistence.model.message.Message;
import com.seafta.service.domain.persistence.repository.MessageRepository;
import com.seafta.service.domain.request.message.MessageRequest;
import com.seafta.service.domain.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    AccountService accountService;

    @Override
    public void sendMessage(MessageRequest request) {
        String senderName = accountService.getNameByAccountId(request.getAccountId());
        String recipientName = accountService.getNameByAccountId(request.getCompanyId());
        Message message = Message.buildMessage(request, senderName, recipientName);
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessage(UUID accountId, UUID companyId) {
        List<Message> messages = messageRepository.findAllByAccountIdAndCompanyIdOrderByCreated(accountId, companyId);
        return messages;
    }

    @Override
    public List<Message> getMessageByAccountId(UUID accountId) {
        List<Message> messages = messageRepository.findAllByAccountIdOrderByCreated(accountId);
        return messages;
    }

    @Override
    public List<Message> getMessageByCompanyId(UUID accountId) {
        List<Message> messages = messageRepository.findAllByCompanyIdOrderByCreated(accountId);
        return messages;
    }


}
