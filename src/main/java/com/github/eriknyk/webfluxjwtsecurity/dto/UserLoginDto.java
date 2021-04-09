package com.github.eriknyk.webfluxjwtsecurity.dto;

import lombok.Data;

/**
 * UserLoginDto class
 *
 * @author Erik Amaru Ortiz
 */
@Data
public class UserLoginDto {
    private String username;
    private String password;
}
