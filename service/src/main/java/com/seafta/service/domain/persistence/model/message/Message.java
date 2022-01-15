package com.seafta.service.domain.persistence.model.message;


import com.seafta.service.domain.request.message.MessageRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @NotNull
    private UUID accountId;

    @NotNull
    private UUID companyId;

    @NotBlank
    private String senderName;

    @NotBlank
    private String recipientName;

    private String content;

    private OffsetDateTime created;

    public static Message buildMessage(MessageRequest request) {
        return Message.builder()
                .companyId(request.getCompanyId())
                .accountId(request.getAccountId())
                .senderName(request.getSenderName())
                .recipientName(request.getRecipientName())
                .content(request.getContent())
                .created(OffsetDateTime.now(Clock.systemUTC()))
                .build();
    }
}
