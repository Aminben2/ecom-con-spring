package com.nttdata.spring_boot_app.repository;

import com.nttdata.spring_boot_app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByCategoryIdAndNameContainingOrDescriptionContaining(Long categoryId, String name, String description);
}
