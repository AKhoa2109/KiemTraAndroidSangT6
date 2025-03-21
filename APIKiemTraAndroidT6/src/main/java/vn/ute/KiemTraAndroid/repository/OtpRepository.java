package vn.ute.KiemTraAndroid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ute.KiemTraAndroid.entity.Otp;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Optional<Otp> findByEmailAndOtpCode(String email, String otp);
    void deleteByEmail(String email);
}
