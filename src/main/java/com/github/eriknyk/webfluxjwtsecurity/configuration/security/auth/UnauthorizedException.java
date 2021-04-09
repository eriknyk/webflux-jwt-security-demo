package com.github.eriknyk.webfluxjwtsecurity.configuration.security.auth;

import com.github.eriknyk.webfluxjwtsecurity.app.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UnauthorizedException class
 *
 * @author Erik Amaru Ortiz
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(message, "UNAUTHORIZED");
    }
}
