// Nguyễn Tuấn Thành - 22110418
package vn.ute.KiemTraAndroid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ute.KiemTraAndroid.entity.Product;
import vn.ute.KiemTraAndroid.service.ProductService;


@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("category/{categoryId}")
	public ResponseEntity<List<Product>> getCategoryProduct(@PathVariable Long categoryId )
	{
		List<Product> listCateProduct = productService.findAllByCategoryIdOrderByPriceAsc(categoryId);
		return ResponseEntity.ok(listCateProduct);
			
	}

}
