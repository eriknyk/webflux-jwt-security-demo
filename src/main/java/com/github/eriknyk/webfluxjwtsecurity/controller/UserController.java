package com.github.eriknyk.webfluxjwtsecurity.controller;

import com.github.eriknyk.webfluxjwtsecurity.configuration.security.auth.UserPrincipal;
import com.github.eriknyk.webfluxjwtsecurity.dto.UserDto;
import com.github.eriknyk.webfluxjwtsecurity.dto.mapper.UserMapper;
import com.github.eriknyk.webfluxjwtsecurity.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * UserController class
 *
 * @author Erik Amaru Ortiz
 */
@RestController
@RequestMapping("user")
@PreAuthorize("hasRole('USER')")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public Mono<UserDto> get(Authentication authentication) {
        var principal = (UserPrincipal) authentication.getPrincipal();

        return userService.getUser(principal.getId())
                .map(user -> userMapper.map(user));
    }
}
