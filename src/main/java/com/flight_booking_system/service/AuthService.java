package com.flight_booking_system.service;

import com.flight_booking_system.entity.User;
import com.flight_booking_system.exception.ConflictException;
import com.flight_booking_system.exception.InvalidCredentialsException;
import com.flight_booking_system.model.res.UserLoginRequest;
import com.flight_booking_system.model.res.UserRegisterRequest;
import com.flight_booking_system.model.res.UserResponse;
import com.flight_booking_system.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public UserResponse register(UserRegisterRequest request) {
        Optional<User> existing = userRepo.findByUsername(request.getUsername());
        if (existing.isPresent()) {
            throw new ConflictException("username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        userRepo.save(user);

        return new UserResponse("registered successfully");
    }

    public UserResponse login(UserLoginRequest request) {
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("invalid username or password"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("invalid username or password");
        }

        return new UserResponse("login successful");
    }
}
