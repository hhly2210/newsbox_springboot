package com.tintucspringboot.tintuc.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tintucspringboot.tintuc.model.AdminMenu;

public interface MenuAdminAsideRepository extends JpaRepository<AdminMenu, Integer>{
    @Query("select u from AdminMenu u where u.parent is null")
    List<AdminMenu> getMenus();

    @Query("""
            select a from AdminMenu a inner join a.roles.users users
            where users.id = ?1 and a.active = ?2 and a.parent is null""")
    List<AdminMenu> findByRoles_Users_IdAndActiveAndParentNull(int id, boolean active);

}
