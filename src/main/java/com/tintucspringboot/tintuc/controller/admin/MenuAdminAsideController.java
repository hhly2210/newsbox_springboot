package com.tintucspringboot.tintuc.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tintucspringboot.tintuc.dto.AdminMenuDto;
import com.tintucspringboot.tintuc.dto.AdminMenuEditDto;
import com.tintucspringboot.tintuc.mapper.AdminMenuEditMapper;
import com.tintucspringboot.tintuc.mapper.AdminMenuMapper;
import com.tintucspringboot.tintuc.model.AdminMenu;
import com.tintucspringboot.tintuc.respository.MenuAdminAsideRepository;
import com.tintucspringboot.tintuc.respository.RoleRepository;

import jakarta.persistence.PrimaryKeyJoinColumn;

@Controller
@RequestMapping("/admin/menus-aside")
public class MenuAdminAsideController {

    @Autowired
    private AdminMenuMapper mapper;
    
    @Autowired
    private AdminMenuEditMapper editMapper; 
    
    @Autowired
    private MenuAdminAsideRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("contentFragment", "admin/menu-admin-aside/index.html");
        var menuAside = repository.findAll().toArray();
        model.addAttribute("menuAsides", menuAside);
        model.addAttribute("modalDelete", "admin/menu-admin-aside/delete.html");
        return "admin/layouts/app";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("contentFragment", "admin/menu-admin-aside/create.html");
        model.addAttribute("adminMenu", new AdminMenu());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("menuAsides", repository.findAll());
        return "admin/layouts/app";
    }

    @PostMapping("/create")
    public String creteAdminMenu(@ModelAttribute("adminMenu") AdminMenuDto dto) {
        AdminMenu newAdminMenu = mapper.convert(dto);
        repository.save(newAdminMenu);
        return "redirect:/admin/menus-aside";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        AdminMenu adminMenu = repository.getReferenceById(id);
        model.addAttribute("adminMenu", adminMenu);
        var menu = repository.findAll();
        menu.removeIf(i -> i.getId() == id);
        model.addAttribute("menus", menu);
        model.addAttribute("contentFragment", "admin/menu-admin-aside/edit.html");
        return "admin/layouts/app";
    }

    @PostMapping("/edit")
    public String editAdminMenu(@ModelAttribute AdminMenuEditDto dto) {
        AdminMenu adminMenu = repository.getReferenceById(dto.id());
        editMapper.patch(dto, adminMenu);
        repository.save(adminMenu);
        return "redirect:/admin/menus-aside";
    }

    @PostMapping("/delete")
    public String deleteuser(@RequestParam int id) {
        repository.deleteById(id);
        return "redirect:/admin/menus-aside";
    }
}
