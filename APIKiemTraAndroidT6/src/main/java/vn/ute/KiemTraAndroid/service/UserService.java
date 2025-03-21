package vn.ute.KiemTraAndroid.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import vn.ute.KiemTraAndroid.dto.request.LoginRequest;
import vn.ute.KiemTraAndroid.dto.response.LoginResponse;
import vn.ute.KiemTraAndroid.dto.response.UserResponse;
import vn.ute.KiemTraAndroid.entity.Otp;
import vn.ute.KiemTraAndroid.entity.User;
import vn.ute.KiemTraAndroid.repository.OtpRepository;
import vn.ute.KiemTraAndroid.repository.UserRepository;
// Quảng Đại Thiện - 22110426
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

    //Le Dinh Loc - 22110369
    public UserResponse getUserById(int id) {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
//            return UserResponse.builder()
//                    .userId(user.getUserId())
//                    .image(user.getImage())
//                    .name(user.getName())
//                    .email(user.getEmail())
//            .build();
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setImage(user.getImage());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            return userResponse;
        }
        return null;
    }

    public boolean register(String name, String email, String confirmPassword, String password) {
        if (userRepository.existsByEmail(email)) {
            return false;
        }
        if (!password.equals(confirmPassword)) {
            return false;
        }
        User user = new User(name, email, password);
        user.setActive(true);
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
                userRepository.save(user);
                otpRepository.deleteByEmail(email);
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
            helper.setText("OTP c?a b?n l�: " + otp);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    /*Huynh Thai Toan 22110436*/


    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            return new LoginResponse("User not found", null, -1);
        }

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            return new LoginResponse("Invalid password", null, -1);
        }
        if (!user.isActive()) {
            return new LoginResponse("Account is not active", null, -1);
        }

        return new LoginResponse("Login successful", user.getEmail(), user.getId());
    }
    /*Huynh Thai Toan 22110436*/
}
