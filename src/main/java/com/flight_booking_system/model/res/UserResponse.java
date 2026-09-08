package com.flight_booking_system.model.res;

public class UserResponse {
    private String message;

    public UserResponse(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
