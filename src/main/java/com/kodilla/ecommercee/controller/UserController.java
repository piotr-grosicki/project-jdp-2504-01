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
    public ResponseEntity<String> generateToken(@RequestBody User userRequest) {
        //Nie używam jeszcze bazy (wersję mockowaną)
        // Lista "mockowanych" użytkowników (symulacja bazy danych)
        // Sztuczne generowanie tokenu (ważny 1h)
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "john.doe@example.com", false, LocalDateTime.now()));
        users.add(new User(2L, "jane.smith@example.com", true, LocalDateTime.now()));
        // Znajdź użytkownika po e-mailu
        return users.stream()
                .filter(u -> u.getEmail().equals(userRequest.getEmail()))
                .findFirst()
                .map(u -> {
                    if (u.isBlocked()) {
                        return ResponseEntity.status(403).body("Użytkownik jest zablokowany.");
                    }
                    String token = java.util.UUID.randomUUID().toString();
                    LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);
                    // Generowanie tokena
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    String formattedDate = expiresAt.format(formatter);
                    String response = "Token: " + token + "\nWażny do: " + formattedDate;
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(404).body("Użytkownik nie istnieje."));
    }
}
