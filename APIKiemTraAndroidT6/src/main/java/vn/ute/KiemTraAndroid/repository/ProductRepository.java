// Nguyễn Tuấn Thành - 22110418
package vn.ute.KiemTraAndroid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ute.KiemTraAndroid.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	 
    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId ORDER BY p.price ASC")
    List<Product> findAllByCategoryIdOrderByPriceAsc(Long categoryId);

}
