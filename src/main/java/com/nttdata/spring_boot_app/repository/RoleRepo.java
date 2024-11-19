package com.nttdata.spring_boot_app.repository;

import com.nttdata.spring_boot_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
