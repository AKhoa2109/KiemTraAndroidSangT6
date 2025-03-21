package vn.ute.KiemTraAndroid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ute.KiemTraAndroid.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String email = request.get("email");
        String password = request.get("password");
        String confirmPassword = request.get("confirmPassword");
        boolean success = userService.register(name, email, password, confirmPassword);
        if (success) {
            return ResponseEntity.ok("Đăng ký thành công. Kiểm tra OTP được gửi qua email");
        } else {
            return ResponseEntity.badRequest().body("Đã có lỗi");
        }
    }

    @PostMapping("/activate")
    public ResponseEntity<String> active(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        boolean success = userService.active(email, otp);
        if (success) {
            return ResponseEntity.ok("Kích hoạt tài khoản thành công.");
        } else {
            return ResponseEntity.badRequest().body("OTP hoặc email không hợp lệ.");
        }
    }
}
