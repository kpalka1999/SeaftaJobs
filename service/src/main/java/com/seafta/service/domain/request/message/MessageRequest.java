package com.seafta.service.domain.request.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageRequest {

    @NotNull
    private UUID accountId;

    @NotNull
    private UUID companyId;

    private String content;
}
