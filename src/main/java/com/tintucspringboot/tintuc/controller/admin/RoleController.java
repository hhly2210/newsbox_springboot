package com.tintucspringboot.tintuc.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tintucspringboot.tintuc.dto.RoleDto;
import com.tintucspringboot.tintuc.dto.RoleEditDto;
import com.tintucspringboot.tintuc.mapper.RoleEditMapper;
import com.tintucspringboot.tintuc.mapper.RoleMapper;
import com.tintucspringboot.tintuc.model.Role;
import com.tintucspringboot.tintuc.respository.RoleRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {
    @Autowired
    private RoleMapper mapper;

    @Autowired RoleEditMapper editMapper;

    @Autowired
    private RoleRepository repository;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("contentFragment", "admin/roles/index.html");
        var role = repository.findAll().toArray();
        model.addAttribute("roles", role);
        model.addAttribute("modalDelete", "admin/roles/delete.html");
        return "admin/layouts/app";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("contentFragment", "admin/roles/create.html");
        // model.addAttribute("roles", repository.findAll());
        return "admin/layouts/app";
    }

    @PostMapping("/create")
    public String createRole(@ModelAttribute RoleDto dto) {
        Role newRole = mapper.convert(dto);
        repository.save(newRole);
        return "redirect:/admin/roles";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Role role = repository.getReferenceById(id);
        model.addAttribute("role", role);
        model.addAttribute("contentFragment", "admin/roles/edit.html");
        return "admin/layouts/app";
    }

    @PostMapping("/edit")
    public String editRole(@ModelAttribute RoleEditDto dto) {
        Role role = repository.getReferenceById(dto.id());
        editMapper.patch(dto, role);
        repository.save(role);
        return "redirect:/admin/roles";
    }

    @PostMapping("/delete")
    public String deleteRole(@RequestParam int id) {
        repository.deleteById(id);
        return "redirect:/admin/roles";
    }
}
