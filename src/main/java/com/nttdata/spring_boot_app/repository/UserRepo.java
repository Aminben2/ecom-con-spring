package com.nttdata.spring_boot_app.repository;

import com.nttdata.spring_boot_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
