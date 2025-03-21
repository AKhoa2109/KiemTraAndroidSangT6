// Nguyễn Lý Hùng - 22110337
package vn.ute.KiemTraAndroid.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ute.KiemTraAndroid.entity.Category;
import vn.ute.KiemTraAndroid.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	 private final CategoryService categoryService;

	    public CategoryController(CategoryService categoryService) {
	        this.categoryService = categoryService;
	    }

	    @GetMapping
	    public List<Category> getAllCategories() {
	        return categoryService.getAllCategories();
	    }
}
