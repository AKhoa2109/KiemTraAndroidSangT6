package vn.ute.KiemTraAndroid.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.ute.KiemTraAndroid.entity.Otp;
// Quảng Đại Thiện - 22110426
public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Optional<Otp> findByEmailAndOtpCode(String email, String otp);
    void deleteByEmail(String email);
}
