package com.tintucspringboot.tintuc.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tintucspringboot.tintuc.config.CustomUserDetails;
import com.tintucspringboot.tintuc.model.AdminMenu;
import com.tintucspringboot.tintuc.respository.MenuAdminAsideRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminMenuService {
    private final MenuAdminAsideRepository repository;

    public List<AdminMenu> getAdminMenus() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (CustomUserDetails) authentication.getPrincipal();
        return repository.findByRoles_Users_IdAndActiveAndParentNull(user.getId(), true); 
    }
}
