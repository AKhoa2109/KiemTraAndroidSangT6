// Nguyễn Lý Hùng - 22110337
package vn.ute.KiemTraAndroid.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import vn.ute.KiemTraAndroid.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}

