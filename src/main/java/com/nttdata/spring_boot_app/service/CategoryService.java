package com.nttdata.spring_boot_app.service;

import com.nttdata.spring_boot_app.entity.Category;
import com.nttdata.spring_boot_app.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Transactional
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Transactional
    public Category updateCategory(Category category) {
        Category categoryToUpdate = categoryRepo.findById(category.getId()).orElse(null);
        if (categoryToUpdate == null) {
            return null;
        }
        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setDescription(category.getDescription());
        return categoryRepo.save(categoryToUpdate);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
