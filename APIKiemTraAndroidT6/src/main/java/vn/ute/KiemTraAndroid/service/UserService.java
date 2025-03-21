package vn.ute.KiemTraAndroid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.ute.KiemTraAndroid.dto.response.UserResponse;
import vn.ute.KiemTraAndroid.entity.User;
import vn.ute.KiemTraAndroid.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse getUserById(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
//            return UserResponse.builder()
//                    .userId(user.getUserId())
//                    .image(user.getImage())
//                    .name(user.getName())
//                    .email(user.getEmail())
//            .build();
            UserResponse userResponse = new UserResponse();
            userResponse.setUser_id(user.getUser_id());
            userResponse.setImage(user.getImage());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            return userResponse;
        }
        return null;
    }
}
