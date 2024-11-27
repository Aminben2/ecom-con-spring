package com.nttdata.spring_boot_app.repository;

import com.nttdata.spring_boot_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("http://localhost:5173")
@RepositoryRestResource(excerptProjection = User.class)
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
