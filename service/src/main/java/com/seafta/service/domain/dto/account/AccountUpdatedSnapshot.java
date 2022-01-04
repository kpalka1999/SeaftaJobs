package com.seafta.service.domain.dto.account;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdatedSnapshot {

  @NotNull
  private UUID accountId;

  @NotNull
  private String email;
}