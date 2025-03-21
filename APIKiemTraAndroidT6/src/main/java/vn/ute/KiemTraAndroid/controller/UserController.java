package vn.ute.KiemTraAndroid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.ute.KiemTraAndroid.dto.response.UserResponse;
import vn.ute.KiemTraAndroid.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

//	@GetMapping("/{id}")
//    public UserResponse getUserById(@PathVariable int id) {
//        return userService.getUserById(id);
//    }

    @PostMapping(value = "/register", produces = "text/plain; charset=UTF-8")
    public ResponseEntity<String> register(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String email = request.get("email");
        String password = request.get("password");
        String confirmPassword = request.get("confirmPassword");
        boolean success = userService.register(name, email, password, confirmPassword);
        if (success) {
            return ResponseEntity.ok("Đăng ký thành công. OTP gửi qua mail");
        } else {
            return ResponseEntity.badRequest().body("Có lỗi");
        }
    }

    @PostMapping("/activate")
    public ResponseEntity<String> active(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        boolean success = userService.active(email, otp);
        if (success) {
            return ResponseEntity.ok("K�ch ho?t t�i kho?n th�nh c�ng.");
        } else {
            return ResponseEntity.badRequest().body("OTP ho?c email kh�ng h?p l?.");
        }
    }
}
