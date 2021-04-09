package com.github.eriknyk.webfluxjwtsecurity.controller;

import com.github.eriknyk.webfluxjwtsecurity.dto.UserDto;
import com.github.eriknyk.webfluxjwtsecurity.dto.mapper.UserMapper;
import com.github.eriknyk.webfluxjwtsecurity.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * PublicController class
 *
 * @author Erik Amaru Ortiz
 */
@RestController
@RequestMapping("public")
public class PublicController {
    private final UserService userService;
    private final UserMapper userMapper;

    public PublicController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Only for demo purpose!!!
     * @return Mono<User>
     */
    @PostMapping("/demo-user")
    public Mono<UserDto> newUser(@RequestBody UserDto userDto) {
        var user = userMapper.map(userDto);
        return userService.createUser(user)
                .map(u -> userMapper.map(u));
    }

    @GetMapping("/version")
    public Mono<String> version() {
        return Mono.just("1.0.0");
    }
}
