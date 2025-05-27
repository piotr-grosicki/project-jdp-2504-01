package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
        public ResponseEntity<List<UserDto>> getUsers() {
            List<UserDto> users = new ArrayList<>();
            users.add(new UserDto(
                    1L, "John", "Doe", "john.doe@example.com", false, LocalDateTime.now()));
            users.add(new UserDto(
                    2L, "Jane", "Smith", "jane.smith@example.com", true, LocalDateTime.now()));
            return ResponseEntity.ok(users);
        }

    @GetMapping("/{userId}")
        public ResponseEntity<UserDto> getUser(@PathVariable long userId) {
        UserDto user = new UserDto(
                userId, "Mock", "User", "mock.user@example.com", false, LocalDateTime.now());
        return ResponseEntity.ok(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
            UserDto createdUser  = new UserDto(
                    100L, "FirstName", "LastName", user.getEmail(), false, LocalDateTime.now());
            return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/block/{userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId){
        UserDto blockedUser = new UserDto(
                userId, "BlockedFirstName", "BlockedLastName", "blocked@example.com", true, LocalDateTime.now());
        return ResponseEntity.ok(blockedUser);
    }

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody UserDto user) {
        // Sztuczne generowanie tokenu (ważny 1h)
        String token = "mocked-token-12345";
        return ResponseEntity.ok(token);
    }
}
