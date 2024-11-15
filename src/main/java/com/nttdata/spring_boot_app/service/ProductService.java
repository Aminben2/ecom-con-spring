package com.nttdata.spring_boot_app.service;

import com.nttdata.spring_boot_app.entity.Category;
import com.nttdata.spring_boot_app.entity.Product;
import com.nttdata.spring_boot_app.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public List<Product> getProductsByKeyword(String keyword) {
        return productRepo.findByNameContainingOrDescriptionContaining(keyword, keyword);
    }

    public List<Product> getProductsByCategoryAndKeyword(Long categoryId, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return productRepo.findByCategoryId(categoryId);
        } else {
            return productRepo.findByCategoryIdAndNameContainingOrDescriptionContaining(categoryId, keyword, keyword);
        }
    }

    @Transactional
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Transactional
    public Product updateProduct(Product product) {
        Product productToUpdate = productRepo.findById(product.getId()).orElse(null);
        if (productToUpdate == null) {
            return null;
        }
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setQuantity(product.getQuantity());
        productToUpdate.setImage(product.getImage());
        Category category = new Category();
        category.setId(product.getCategory().getId());
        productToUpdate.setCategory(category);
        return productRepo.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

//    public List<Product> getProductsByCategoryId(Long categoryId) {
//        return productRepo.findByCategoryId(categoryId);
//    }
//
//    public List<Product> getProductsByCategoryName(String categoryName) {
//        return productRepo.findByCategoryName(categoryName);
//    }
}
