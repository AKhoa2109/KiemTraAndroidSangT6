// Nguyễn Tuấn Thành - 22110418
package vn.ute.KiemTraAndroid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ute.KiemTraAndroid.entity.Product;
import vn.ute.KiemTraAndroid.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAllByCategoryIdOrderByPriceAsc(Long categoryId) {
		return productRepository.findAllByCategoryIdOrderByPriceAsc(categoryId);
	}

	
	
	
	

}
