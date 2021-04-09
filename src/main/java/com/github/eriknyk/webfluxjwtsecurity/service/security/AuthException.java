package com.github.eriknyk.webfluxjwtsecurity.service.security;

import com.github.eriknyk.webfluxjwtsecurity.app.ApiException;

/**
 * AuthException class
 *
 * @author Erik Amaru Ortiz
 */
public class AuthException extends ApiException {
    public AuthException(String message, String errorCode) {
        super(message, errorCode);
    }
}
