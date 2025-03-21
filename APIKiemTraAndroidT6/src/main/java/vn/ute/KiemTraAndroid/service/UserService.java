package vn.ute.KiemTraAndroid.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.ute.KiemTraAndroid.entity.Otp;
import vn.ute.KiemTraAndroid.entity.User;
import vn.ute.KiemTraAndroid.repository.OtpRepository;
import vn.ute.KiemTraAndroid.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    private JavaMailSender javaMailSender;
    private UserRepository userRepository;
    private OtpRepository otpRepository;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    public UserService(JavaMailSender javaMailSender, UserRepository userRepository, OtpRepository otpRepository) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
    }

    public boolean register(String name, String email, String confirmPassword, String password) {
        if (userRepository.existsByEmail(email)) {
            return false;
        }
        if (!password.equals(confirmPassword)) {
            return false;
        }
        User user = new User(name, email, password);
        userRepository.save(user);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusMinutes(5);
        String otp = generateOTP();
        Otp otpEntity = new Otp(email, otp, now, expiresAt);
        otpRepository.save(otpEntity);
        sendOtpEmail(email, otp, "Kích hoạt tài khoản của bạn");
        return true;
    }

    @Transactional
    public boolean active(String email, String otp) {
        Optional<Otp> otpOptional = otpRepository.findByEmailAndOtpCode(email, otp);
        if (otpOptional.isPresent()) {
            Otp otpEntity = otpOptional.get();
            LocalDateTime now = LocalDateTime.now();

            if (now.isAfter(otpEntity.getExpiresAt())) {
                otpRepository.deleteByEmail(email);
                return false;
            }

            User user = userRepository.findByEmail(email);
            if (user != null) {
                user.setActive(true);
                userRepository.save(user); // Cập nhật trạng thái trong DB
                otpRepository.deleteByEmail(email); // Xóa OTP sau khi kích hoạt thành công
                return true;
            }
        }
        return false;
    }

    public String generateOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    public void sendOtpEmail(String email, String otp, String subject) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText("OTP của bạn là: " + otp);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
