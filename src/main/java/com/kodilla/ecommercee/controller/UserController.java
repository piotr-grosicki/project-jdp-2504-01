package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(mapper.mapToUserDtoList(service.getAllUsers()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable long userId) throws UserNotFoundException {
        return ResponseEntity.ok(mapper.mapToUserDto(service.getUserById(userId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(mapper.mapToUserDto(service.createUser(mapper.mapToUser(userDto))));
    }

    @PutMapping("/block/{userId}")
    public ResponseEntity<Boolean> blockUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(service.blockUser(userId));
    }

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(service.generateToken(mapper.mapToUser(userDto)));
    }
}
