// Nguyễn Lý Hùng - 22110337
package vn.ute.KiemTraAndroid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.ute.KiemTraAndroid.entity.Category;
import vn.ute.KiemTraAndroid.repository.CategoryRepository;
@Service

public class CategoryService {
	private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
