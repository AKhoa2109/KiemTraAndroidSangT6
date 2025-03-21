//Huynh Thai Toan 22110436
package com.android.projectnhom.entity;

public class LoginResponse {
    private String message;
    private String email;
    private int userId;

    public LoginResponse(String message, String email, int userId) {
        this.message = message;
        this.email = email;
        this.userId = userId;
    }

    // Getter và Setter
    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public int getUserId() {
        return userId;
    }
}
//Huynh Thai Toan 22110436
