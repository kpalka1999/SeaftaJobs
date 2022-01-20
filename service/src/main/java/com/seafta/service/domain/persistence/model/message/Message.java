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

    private String content;

    private String senderName;

    private String recipientName;

    private OffsetDateTime created;

    public static Message buildMessage(MessageRequest request, String senderName, String recipientName) {
        return Message.builder()
                .companyId(request.getCompanyId())
                .accountId(request.getAccountId())
                .content(request.getContent())
                .senderName(senderName)
                .recipientName(recipientName)
                .created(OffsetDateTime.now(Clock.systemUTC()))
                .build();
    }
}
