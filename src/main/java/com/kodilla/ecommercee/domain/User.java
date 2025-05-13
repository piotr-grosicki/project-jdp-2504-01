package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    private Long id;
    private String email;
    private boolean isBlocked;
    private LocalDateTime createdAt;
}
