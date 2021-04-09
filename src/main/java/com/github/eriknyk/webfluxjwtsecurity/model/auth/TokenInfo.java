package com.github.eriknyk.webfluxjwtsecurity.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * TokenInfo class
 *
 * @author Erik Amaru Ortiz
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfo {
    private Long userId;
    private String token;
    private Date issuedAt;
    private Date expiresAt;
}
