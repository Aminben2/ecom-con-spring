package com.nttdata.spring_boot_app.controller;

import com.nttdata.spring_boot_app.entity.Category;
import com.nttdata.spring_boot_app.service.CategoryService;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

  CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/")
  public String getCategories(Map<String, Object> model) {
    model.put("categories", categoryService.getAllCategories());
    return "/category/categories";
  }

  @GetMapping("/add")
  public String addCategory(Map<String, Object> model) {
    Category categoryDto = new Category();
    model.put("category", categoryDto);
    model.put("categories", categoryService.getAllCategories());
    return "/category/categories";
  }

  @GetMapping("/edit/{id}")
  public String editCategory(Map<String, Object> model, @PathVariable Long id) {
    Category category = categoryService.getCategoryById(id);
    model.put("category", category);
    model.put("categories", categoryService.getAllCategories());
    return "/category/categories";
  }

  @PostMapping("/update")
  public String updateCategory(Map<String, Object> model, Category category) {
    categoryService.updateCategory(category);
    model.put("categories", categoryService.getAllCategories());
    return "/category/categories";
  }

  @PostMapping("/save")
  public String createCategory(Map<String, Object> model, Category category) {
    categoryService.addCategory(category);
    model.put("categories", categoryService.getAllCategories());
    return "/category/categories";
  }

  @GetMapping("/{id}")
  public String deleteCategory(Map<String, Object> model, @PathVariable Long id) {
    categoryService.deleteCategory(id);
    model.put("categories", categoryService.getAllCategories());
    return "/category/categories";
  }
}
