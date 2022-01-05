package com.seafta.service.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seafta.service.domain.persistence.model.account.Account;
import com.seafta.service.domain.persistence.model.account.AccountRole;
import com.seafta.service.domain.request.account.AccountCreateRequest;
import com.seafta.service.domain.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.seafta.auth.utils.SecurityConstants.EXPIRATION_TIME;
import static com.seafta.auth.utils.SecurityConstants.SECRET;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final AccountService accountService;

    @GetMapping("/test")
    @ResponseStatus(OK)
    public String getTest() {
        return "JD";
    }

    @GetMapping("/users")
    public ResponseEntity<List<Account>> getAccount() {
      return ResponseEntity.ok().body(accountService.getAccounts());
  }

    @PostMapping("/users/save")
    public String getAccount(AccountCreateRequest request) {

        return accountService.createAccount(request).toString();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer".length());
                Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes(StandardCharsets.UTF_8));
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                Account user = accountService.getAccountByUsername(username);
                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .withIssuer(request.getRequestURI())
                        .withClaim("roles", user.getRoles().stream().map(AccountRole::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Cookie accessTokenCookie = new Cookie("access_token","Bearer" + accessToken);
                accessTokenCookie.setPath("/");
                accessTokenCookie.setSecure(false);
                accessTokenCookie.setHttpOnly(true);

                Cookie refreshTokenCookie = new Cookie("refresh_token", "Bearer" + refreshToken);
                refreshTokenCookie.setPath("/");
                refreshTokenCookie.setSecure(false);
                refreshTokenCookie.setHttpOnly(true);
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.addCookie(accessTokenCookie);
                response.addCookie(refreshTokenCookie);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
           throw new RuntimeException("Refresh token is missing");
        }
    }
}
