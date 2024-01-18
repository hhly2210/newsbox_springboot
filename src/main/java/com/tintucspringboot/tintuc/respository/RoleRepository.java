package com.tintucspringboot.tintuc.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tintucspringboot.tintuc.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
