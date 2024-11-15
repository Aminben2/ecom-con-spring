package com.nttdata.spring_boot_app.repository;

import com.nttdata.spring_boot_app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {}