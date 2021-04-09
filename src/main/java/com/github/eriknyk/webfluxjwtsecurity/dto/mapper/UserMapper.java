package com.github.eriknyk.webfluxjwtsecurity.dto.mapper;

import com.github.eriknyk.webfluxjwtsecurity.dto.UserDto;
import com.github.eriknyk.webfluxjwtsecurity.model.user.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * UserMapper class
 *
 * @author Erik Amaru Ortiz
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(User user);

    @InheritInverseConfiguration
    User map(UserDto userDto);
}
