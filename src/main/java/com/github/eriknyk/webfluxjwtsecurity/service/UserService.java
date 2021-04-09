package com.github.eriknyk.webfluxjwtsecurity.service;

import com.github.eriknyk.webfluxjwtsecurity.model.auth.TokenInfo;
import com.github.eriknyk.webfluxjwtsecurity.model.user.User;
import com.github.eriknyk.webfluxjwtsecurity.model.user.UserRepository;
import com.github.eriknyk.webfluxjwtsecurity.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * UserService class
 *
 * @author Erik Amaru Ortiz
 */
@Slf4j
@Service
public class UserService {
    private final SecurityService securityService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(SecurityService securityService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.securityService = securityService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(user.toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build())
                .doOnSuccess(u -> log.info("Created new user with ID = " + u.getId()));
    }

    public Mono<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
