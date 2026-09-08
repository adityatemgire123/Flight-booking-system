package com.flight_booking_system.controller;

import com.flight_booking_system.model.res.UserLoginRequest;
import com.flight_booking_system.model.res.UserRegisterRequest;
import com.flight_booking_system.model.res.UserResponse;
import com.flight_booking_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
