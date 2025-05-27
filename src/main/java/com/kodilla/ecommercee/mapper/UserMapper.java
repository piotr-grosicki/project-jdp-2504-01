package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.isBlocked(), user.getCreatedAt());
    }

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getFirstName(), userDto.getLastName(),
                userDto.getEmail(), userDto.isBlocked(), userDto.getCreatedAt(), null);
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream().map(this::mapToUserDto)
                .toList();
    }
}
