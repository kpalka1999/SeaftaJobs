package com.seafta.service.boundary.message;

import com.seafta.service.domain.persistence.model.message.Message;
import com.seafta.service.domain.request.message.MessageRequest;
import com.seafta.service.domain.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void sendMessage(@RequestBody MessageRequest request) {
        messageService.sendMessage(request);
    }

    @GetMapping(value = "/{accountId}/{companyId}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<Message> getMessage(@PathVariable("accountId") UUID accountId, @PathVariable("companyId") UUID companyId) {
        return messageService.getMessage(accountId, companyId);
    }
}
