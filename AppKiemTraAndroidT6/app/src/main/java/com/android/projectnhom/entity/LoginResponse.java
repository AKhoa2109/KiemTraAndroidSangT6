package com.android.projectnhom.entity;

public class LoginResponse {
    private String message;
    private String email;
    private int id;

    public LoginResponse(String message, String email, int id) {
        this.message = message;
        this.email = email;
        this.id = id;
    }

    // Getter và Setter
    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
