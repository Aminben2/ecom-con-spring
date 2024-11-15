package com.nttdata.spring_boot_app.repository;

import com.nttdata.spring_boot_app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {}
