package com.flight_booking_system.model.res;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}