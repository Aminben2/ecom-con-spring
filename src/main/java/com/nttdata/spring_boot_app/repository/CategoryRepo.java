package com.nttdata.spring_boot_app.repository;

import com.nttdata.spring_boot_app.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(excerptProjection = Category.class)
public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category>  findByNameContaining(@RequestParam String name);
}
