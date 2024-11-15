package com.nttdata.spring_boot_app.controller;

import com.nttdata.spring_boot_app.entity.Product;
import com.nttdata.spring_boot_app.service.CategoryService;
import com.nttdata.spring_boot_app.service.ProductService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {

  ProductService productService;
  CategoryService categoryService;

  public ProductController(ProductService productService, CategoryService categoryService) {
    this.productService = productService;
    this.categoryService = categoryService;
  }

  @GetMapping("/")
  public String getProducts(Map<String, Object> model) {
    model.put("products", productService.getAllProducts());
    model.put("categories", categoryService.getAllCategories());
    return "product/products";
  }

  @GetMapping("/add")
  public String addProduct(Map<String, Object> model) {
    model.put("product", new Product());
    model.put("categories", categoryService.getAllCategories());
    model.put("products", productService.getAllProducts());
    return "product/products";
  }

  @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String createProduct(
      Map<String, Object> model,
      @RequestPart("productImage") MultipartFile image,
      @ModelAttribute Product product) {

    if (!image.isEmpty()) {
      String imageName = saveImage(image);
      product.setImage(imageName);
    }

    productService.addProduct(product);
    Product newProduct = new Product();
    model.put("products", productService.getAllProducts());
    model.put("categories", categoryService.getAllCategories());
    model.put("product", newProduct);
    return "product/products";
  }

  private String saveImage(MultipartFile image) {
    String originalFilename = image.getOriginalFilename();
    String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
    String uniqueImageName = UUID.randomUUID().toString() + extension;
    Path imagePath = Paths.get("src/main/resources/static/uploads", uniqueImageName);
    try {
      Files.createDirectories(imagePath.getParent());
      Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return uniqueImageName;
  }

  @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String updateProduct(
      Model model,
      @RequestParam("productImage") MultipartFile image,
      @ModelAttribute Product product) {
    if (!image.isEmpty()) {
      String imageName = saveImage(image);
      product.setImage(imageName);
    }

    productService.updateProduct(product);
    model.addAttribute("products", productService.getAllProducts());
    model.addAttribute("categories", categoryService.getAllCategories());
    return "product/products";
  }

  @GetMapping("/edit/{id}")
  public String editProduct(Map<String, Object> model, @PathVariable Long id) {
    Product product = productService.getProductById(id);
    model.put("product", product);
    model.put("products", productService.getAllProducts());
    model.put("categories", categoryService.getAllCategories());
    return "product/products";
  }

  @GetMapping("/{id}")
  public String deleteProduct(Map<String, Object> model, @PathVariable Long id) {
    productService.deleteProduct(id);
    model.put("products", productService.getAllProducts());
    model.put("categories", categoryService.getAllCategories());
    return "product/products";
  }
}
