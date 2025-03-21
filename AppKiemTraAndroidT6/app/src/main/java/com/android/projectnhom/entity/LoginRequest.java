//Huynh Thai Toan 22110436
package com.android.projectnhom.entity;

public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter và Setter (có thể dùng Lombok nếu bạn thêm dependency)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
//Huynh Thai Toan 22110436
