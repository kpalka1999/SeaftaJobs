package com.seafta.service.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdatePasswordRequest {

  @NotBlank
  @Schema(example = "oldPassword")
  private String oldPassword;

  @NotBlank
  @Schema(example = "newPassword")
  private String newPassword;

  @NotBlank
  @Schema(example = "repeatPassword")
  private String repeatPassword;
}
