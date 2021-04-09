package com.github.eriknyk.webfluxjwtsecurity.controller;

import com.github.eriknyk.webfluxjwtsecurity.dto.AuthResultDto;
import com.github.eriknyk.webfluxjwtsecurity.dto.UserLoginDto;
import com.github.eriknyk.webfluxjwtsecurity.service.security.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * AuthController class
 *
 * @author Erik Amaru Ortiz
 */
@RestController
public class AuthController {
    private final SecurityService securityService;

    public AuthController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@RequestBody UserLoginDto dto) {
        return securityService.authenticate(dto.getUsername(), dto.getPassword())
                .flatMap(tokenInfo -> Mono.just(ResponseEntity.ok(AuthResultDto.builder()
                        .userId(tokenInfo.getUserId())
                        .token(tokenInfo.getToken())
                        .issuedAt(tokenInfo.getIssuedAt())
                        .expiresAt(tokenInfo.getExpiresAt())
                        .build())));
    }
}
