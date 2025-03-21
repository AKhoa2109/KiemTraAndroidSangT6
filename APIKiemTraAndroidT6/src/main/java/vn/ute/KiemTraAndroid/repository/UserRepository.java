package vn.ute.KiemTraAndroid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ute.KiemTraAndroid.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

