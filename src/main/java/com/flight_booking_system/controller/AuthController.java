package com.flight_booking_system.controller;

import com.flight_booking_system.model.User;
import com.flight_booking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        Optional<User> existing = userRepo.findByUsername(user.getUsername());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("username already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);

        return ResponseEntity.ok("registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> found = userRepo.findByUsername(user.getUsername());

        if (found.isEmpty()) {
            return ResponseEntity.status(401).body("invalid username or password");
        }

        boolean matches = encoder.matches(user.getPassword(), found.get().getPassword());
        if (!matches) {
            return ResponseEntity.status(401).body("invalid username or password");
        }

        return ResponseEntity.ok("login successful");
    }
}