package com.example.mavenspringapi.mag.model.login;

import java.time.LocalDateTime;

public record LoginAttempt(String email,
                           boolean success,
                           LocalDateTime createdAt) {

}
