package vn.ute.KiemTraAndroid.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ute.KiemTraAndroid.dto.request.LoginRequest;
import vn.ute.KiemTraAndroid.dto.response.LoginResponse;
import vn.ute.KiemTraAndroid.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

//	@GetMapping("/{id}")
//    public UserResponse getUserById(@PathVariable int id) {
//        return userService.getUserById(id);
//    }
// Quảng Đại Thiện - 22110426
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
    // Quảng Đại Thiện - 22110426
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
    
    /*Huynh Thai Toan 22110436*/

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        if (response.getUserId() == -1) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
    /*Huynh Thai Toan 22110436*/
}

