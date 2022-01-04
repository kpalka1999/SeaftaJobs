package com.seafta.service.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountCreateRequest {

    @Email
    @NotBlank
    @Schema(example = "test@gmail.com")
    private String email;

    @NotBlank
    @Schema(example = "password")
    private String password;
}
