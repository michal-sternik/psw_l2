package com.example.demo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        if (category.getId() == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        if (!categoryRepository.existsById(category.getId())) {
            throw new EntityNotFoundException("Category not found");
        }
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        productRepository.deleteAll(productRepository.findByCategoryId(id));
        categoryRepository.deleteById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }
}
