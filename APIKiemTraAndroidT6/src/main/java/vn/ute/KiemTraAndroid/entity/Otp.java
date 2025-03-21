package vn.ute.KiemTraAndroid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;
    @Column(nullable = false)
    private String email;
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
    @Column(name = "otp_code", nullable = false)
    private String otpCode;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Otp(String email, String otpCode, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.email = email;
        this.otpCode = otpCode;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public Otp() {

    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
