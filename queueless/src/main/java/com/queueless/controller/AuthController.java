package com.queueless.controller;

import com.queueless.entity.AppUser;
import com.queueless.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://qms-a-frontend.vercel.app", allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {

        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        try {
            AppUser user = authService.login(username, password);

            return ResponseEntity.ok(user);

        } catch (RuntimeException e) {
            return ResponseEntity.status(401)
                    .body(Map.of("message", "Invalid username or password"));
        }
    }
}
