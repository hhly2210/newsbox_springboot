package com.tintucspringboot.tintuc.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tintucspringboot.tintuc.dto.UserDto;
import com.tintucspringboot.tintuc.dto.UserEditDto;
import com.tintucspringboot.tintuc.dto.UserEditInforDto;
import com.tintucspringboot.tintuc.dto.UserEditPassDto;
import com.tintucspringboot.tintuc.mapper.UserEditInforMapper;
import com.tintucspringboot.tintuc.mapper.UserEditMapper;
import com.tintucspringboot.tintuc.mapper.UserEditPassMapper;
import com.tintucspringboot.tintuc.mapper.UserMapper;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.model.User;
import com.tintucspringboot.tintuc.respository.RoleRepository;
import com.tintucspringboot.tintuc.respository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin/users")

public class UserController {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserEditMapper editMapper;

    @Autowired
    private UserEditInforMapper editInforMapper;

    @Autowired
    private UserEditPassMapper editPassMapper;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("contentFragment", "admin/users/index.html");
        var user = repository.findAll().toArray();
        model.addAttribute("users", user);
        model.addAttribute("modalDelete", "admin/users/delete.html");
        return "admin/layouts/app";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("contentFragment", "admin/users/create.html");
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/layouts/app";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserDto dto) {
        User newUser = mapper.convert(dto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        repository.save(newUser);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        User user = repository.getReferenceById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("contentFragment", "admin/users/edit.html");
        return "admin/layouts/app";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute UserEditDto dto) {
        User user = repository.getReferenceById(dto.id());
        editMapper.patch(dto, user);
        repository.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete")
    public String deleteuser(@RequestParam int id) {
        repository.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/infor")
    public String infor(Model model) {
        model.addAttribute("contentFragment", "admin/users/infor.html");
        model.addAttribute("type", "infor");
        return "admin/layouts/app";
    }

    @PostMapping("/infor/edit")
    public String editInfor(@ModelAttribute UserEditInforDto dto, HttpServletRequest request) {
        User user = repository.getReferenceById(dto.id());
        editInforMapper.patch(dto, user);
        repository.save(user);
        request.getSession().setAttribute("userName", user.getName());
        request.getSession().setAttribute("email", user.getEmail());
        return "redirect:/admin/users/infor";
    }

    @PostMapping("/infor/password")
    public String editPass(@ModelAttribute UserEditPassDto dto, HttpServletRequest request, Model model) {
        try {
            User user = repository.getReferenceById(dto.id());
            editPassMapper.patch(dto, user);
            repository.save(user);
            return "redirect:/admin/users/infor";
        } catch (Exception e) {
            model.addAttribute("contentFragment", "admin/users/infor.html");
            model.addAttribute("errorMessger", "Mật khẩu sai");
            model.addAttribute("type", "password");
            return "admin/layouts/app";
        }
    }

}
