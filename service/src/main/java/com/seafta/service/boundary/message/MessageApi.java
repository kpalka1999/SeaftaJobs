package com.seafta.service.boundary.message;

import com.seafta.service.domain.persistence.model.message.Message;
import com.seafta.service.domain.request.message.MessageRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public interface MessageApi {

    @Operation(summary = "SendMessage", description = "MESSAGE_CREATE_POST")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    void sendMessage(@RequestBody MessageRequest request);

    @Operation(summary = "GetMessage", description = "MESSAGE_GET")
    @GetMapping(value = "/{accountId}/{companyId}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    List<Message> getMessage(@PathVariable("accountId") UUID accountId, @PathVariable("companyId") UUID companyId);

    @Operation(summary = "GetAccountMessages", description = "MESSAGES_ACCOUNT_GET")
    @GetMapping(value = "/account/{accountId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    List<Message> getAccountMessage(@PathVariable("accountId") UUID accountId);

    @Operation(summary = "GetCompanyMessages", description = "MESSAGES_COMPANY_GET")
    @GetMapping(value = "/company/{accountId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    List<Message> getCompanyMessage(@PathVariable("accountId") UUID accountId);
}
