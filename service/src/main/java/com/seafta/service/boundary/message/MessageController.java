package com.seafta.service.boundary.message;

import com.seafta.service.domain.persistence.model.message.Message;
import com.seafta.service.domain.request.message.MessageRequest;
import com.seafta.service.domain.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owasp.security.logging.SecurityMarkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController implements MessageApi{

    @Autowired
    MessageService messageService;

    @Override
    public void sendMessage(@RequestBody MessageRequest request) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Message Controller: creating message {request: {}}", request);
        messageService.sendMessage(request);
        log.trace(SecurityMarkers.CONFIDENTIAL, "Message Controller: created message {result: {}", request);
    }

    @Override
    public List<Message> getMessage(@PathVariable("accountId") UUID accountId, @PathVariable("companyId") UUID companyId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Message Controller: Getting message by ids {from id: {}, to id: {}", accountId, companyId);
        List<Message> result = messageService.getMessage(accountId, companyId);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Message Controller: Got message by ids {from id: {}, to id: {}, result: {}", accountId, companyId, result);
        return result;
    }

    @Override
    public List<Message> getAccountMessage(@PathVariable("accountId") UUID accountId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Message Controller: Getting account messages by id {account ID: {}", accountId);
        List<Message> result = messageService.getMessageByAccountId(accountId);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Message Controller: Got account message by id {account ID: {}, result: {}", accountId, result);
        return result;
    }

    @Override
    public List<Message> getCompanyMessage(@PathVariable("accountId") UUID accountId) {
        log.trace(SecurityMarkers.CONFIDENTIAL, "Message Controller: Getting company messages by id {account ID: {}", accountId);
        List<Message> result = messageService.getMessageByCompanyId(accountId);
        log.debug(SecurityMarkers.CONFIDENTIAL, "Message Controller: Got company message by id {account ID: {}, result: {}", accountId, result);
        return result;
    }
}
