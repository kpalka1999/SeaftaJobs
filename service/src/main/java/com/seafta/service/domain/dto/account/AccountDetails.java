package com.seafta.service.domain.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class AccountDetails {

  @NotNull
  private UUID accountId;

  @NotNull
  @Schema(example = "test@gmail.com")
  private String email;

  private String firstName;

  private String lastName;

  private String description;

  private String mainDescription;

  private String gitHubUrl;

  private boolean isAccount;
}
