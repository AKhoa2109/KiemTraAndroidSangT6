package vn.ute.KiemTraAndroid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ute.KiemTraAndroid.entity.User;
// Quảng Đại Thiện - 22110426
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
