package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
        public ResponseEntity<List<User>> getUsers() {
            List<User> users = new ArrayList<>();
            users.add(new User(1L, "john.doe@example.com", false, LocalDateTime.now()));
            users.add(new User(2L, "jane.smith@example.com", true, LocalDateTime.now()));
            return ResponseEntity.ok(users);
        }

    @GetMapping("/{userId}")
        public ResponseEntity<User> getUser(@PathVariable long userId) {
        User user = new User(userId,"mock.user@example.com", false, LocalDateTime.now());
        return ResponseEntity.ok(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user){
            User createdUser  = new User(100L, user.getEmail(), false, LocalDateTime.now());
            return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/block/{userId}")
    public ResponseEntity<User> blockUser(@PathVariable Long userId){
        User blockedUser = new User(userId, "blocked@example.com", true, LocalDateTime.now());
        return ResponseEntity.ok(blockedUser);
    }

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody User user) {
        // Sztuczne generowanie tokenu (ważny 1h)
        String token = "mocked-token-12345";
        return ResponseEntity.ok(token);
    }
}
