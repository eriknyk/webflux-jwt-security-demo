package com.github.eriknyk.webfluxjwtsecurity.model.user;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * UserRepository class
 *
 * @author Erik Amaru Ortiz
 */
@Repository
public interface UserRepository extends R2dbcRepository<User, Long> {
    Mono<User> findByUsername(String username);
}
