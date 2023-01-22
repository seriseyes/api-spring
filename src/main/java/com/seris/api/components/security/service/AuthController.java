package com.seris.api.components.security.service;

import com.seris.api.entities.user.User;
import com.seris.api.components.security.model.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public record AuthController(
        AuthService authService
) {

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User model) {
        return ResponseEntity.ok(authService.register(model));
    }

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody RegisterDto model) {
        return ResponseEntity.ok(authService.auth(model));
    }
}
